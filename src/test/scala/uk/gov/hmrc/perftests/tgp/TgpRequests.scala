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

import io.gatling.http.request.builder.HttpRequestBuilder
import uk.gov.hmrc.perftests.tgp.Requests.{getPage, postPage}

object TgpRequests extends Configuration {

  implicit class BooleanOps(b: Boolean) {
    def toPayload: Map[String, String] = if (b) Map("value" -> "true") else Map("value" -> "false")
  }

  def getTGPProfilePage: HttpRequestBuilder =
    getPage(
      "TGP Profile Page",
      saveToken = true,
      s"$tgpUrl/trader-goods-profiles/profile-setup"
    )

  def postTGPProfilePage: HttpRequestBuilder =
    postPage(
      "TGP Profile Page",
      s"$tgpUrl/trader-goods-profiles/profile-setup",
      Map[String, String]
    )

  def getUkimsNumberPage: HttpRequestBuilder =
    getPage(
      "UKIMS Number page",
      saveToken = true,
      s"$tgpUrl/trader-goods-profiles/ukims-number"
    )

  def postUkimsNumberPage: HttpRequestBuilder = {
    val enterUkims = Map(
      "value" -> "XIUKIM47699357400020231115081800"
    )
    postPage(
      "enter your UKIMS number",
      s"$tgpUrl/trader-goods-profiles/ukims-number",
      enterUkims
    )
  }

  def getNirmsQuestionPage: HttpRequestBuilder =
    getPage(
      "NIRMS Question page",
      saveToken = true,
      s"$tgpUrl/trader-goods-profiles/nirms-question"
    )

  def postNirmsQuestionPage(answer: Boolean): HttpRequestBuilder =
    postPage(
      "Click Yes or No on NIRMS Question Page",
      s"$tgpUrl/trader-goods-profiles/nirms-question",
      answer.toPayload
    )

  def getNirmsNumberPage: HttpRequestBuilder =
    getPage(
      "NIRMS Number page",
      saveToken = true,
      s"$tgpUrl/trader-goods-profiles/nirms-number"
    )

  def postNirmsNumberPage: HttpRequestBuilder = {
    val enterNirms = Map(
      "value" -> "RMS-GB-123456"
    )
    postPage(
      "enter your NIRMS number",
      s"$tgpUrl/trader-goods-profiles/nirms-number",
      enterNirms
    )
  }

  def getNiphlQuestionPage: HttpRequestBuilder =
    getPage(
      "NIPHL Question page",
      saveToken = true,
      s"$tgpUrl/trader-goods-profiles/niphl-question"
    )

  def postNiphlQuestionPage(answer: Boolean): HttpRequestBuilder =
    postPage(
      "Click Yes or No on NIPHL Question Page",
      s"$tgpUrl/trader-goods-profiles/niphl-question",
      answer.toPayload
    )

  def getNiphlNumberPage: HttpRequestBuilder =
    getPage(
      "NIPHL Number page",
      saveToken = true,
      s"$tgpUrl/trader-goods-profiles/niphl-number"
    )

  def postNiphlNumberPage: HttpRequestBuilder = {
    val enterNiphl = Map(
      "value" -> "SN12345"
    )
    postPage(
      "enter your NIPHL number",
      s"$tgpUrl/trader-goods-profiles/niphl-number",
      enterNiphl
    )
  }

  def getCheckYourAnswersPage: HttpRequestBuilder =
    getPage(
      "Check your answers page",
      saveToken = true,
      s"$tgpUrl/trader-goods-profiles/cya-nirms-niphls"
    )

  def postCheckYourAnswersPage: HttpRequestBuilder =
    postPage(
      "Check your answers page",
      s"$tgpUrl/trader-goods-profiles/cya-nirms-niphls",
      Map.empty[String, String]
    )

  def getHomePage: HttpRequestBuilder =
    getPage(
      "HomePage",
      s"$tgpUrl/trader-goods-profiles/homepage"
    )
}
