/*
 * Copyright 2024 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.perftests.tgp

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import io.netty.handler.codec.http.HttpResponseStatus.{BAD_REQUEST, OK, SEE_OTHER}

object Requests {

  def getPage(
    pageTitle: String,
    saveToken: Boolean,
    url: String,
    pageContent: Option[String] = None,
    expectedStatus: Int = OK.code()
  ): HttpRequestBuilder = {
    val builder = http("GET " + pageTitle)
      .get(url)
      .check(status.is(expectedStatus))
      .check(currentLocation.is(url))
      .check(regex(pageTitle))

    val httpRequestBuilder = pageContent match {
      case Some(value) => builder.check(substring(value))
      case None        => builder
    }

    if (saveToken) {
      httpRequestBuilder.check(css("input[name='csrfToken']", "value").saveAs("csrfToken"))
    } else {
      httpRequestBuilder
    }
  }

  def getPage(pageTitle: String, url: String): HttpRequestBuilder =
    getPage(pageTitle, saveToken = false, url, pageContent = None)

  def postPage(
    pageName: String,
    currentPage: String,
    payload: Map[String, String]
  ): HttpRequestBuilder =
    http("POST " + pageName)
      .post(currentPage)
      .formParamMap(payload + ("csrfToken" -> f"$${csrfToken}"))
      .check(status.is(SEE_OTHER.code()))
      .check(currentLocation.is(currentPage))
      .disableFollowRedirect

  def postPageAndExtractRecordId(
    pageName: String,
    currentPage: String,
    nextPage: String,
    payload: Map[String, String]
  ): HttpRequestBuilder = {

    val extractRecordId: String => String = { (s: String) =>
      s
        .replace("/trader-goods-profiles/create-record/", "")
        .replace(s"/$nextPage", "")
    }

    http("POST " + pageName)
      .post(currentPage)
      .formParamMap(payload + ("csrfToken" -> f"$${csrfToken}"))
      .check(status.is(SEE_OTHER.code()))
      .check(
        header("location")
          .transform(s => extractRecordId(s))
          .saveAs("recordId")
      )
  }
  def postErrorPage(
    pageName: String,
    currentPage: String,
    payload: Map[String, String]
  ): HttpRequestBuilder =
    http("POST " + pageName)
      .post(currentPage)
      .formParamMap(payload + ("csrfToken" -> f"$${csrfToken}"))
      .check(status.is(BAD_REQUEST.code()))
      .check(currentLocation.is(currentPage))
      .disableFollowRedirect
}
