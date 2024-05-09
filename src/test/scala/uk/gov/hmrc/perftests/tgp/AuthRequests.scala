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
import io.netty.handler.codec.http.HttpResponseStatus.{OK, SEE_OTHER}

object AuthRequests extends Configuration {

  private val authWizardUrl: String  = s"$authUrl/auth-login-stub/gg-sign-in"
  private val redirectionUrl: String = "/trader-goods-profiles/profile-setup"

  val navigateToAuthWizard: HttpRequestBuilder =
    http("GET Navigate to /auth-login-stub/gg-sign-in")
      .get(authWizardUrl)
      .check(status.is(OK.code()))
      .check(regex("Authority Wizard"))

  def submitAuthWizard: HttpRequestBuilder =
    http("POST Log in to auth")
      .post(authWizardUrl)
      .formParam("redirectionUrl", redirectionUrl)
      .formParam("credentialStrength", "strong")
      .formParam("authorityId", "")
      .formParam("confidenceLevel", "50")
      .formParam("affinityGroup", "Individual")
      .formParam("credentialRole", "User")
      .formParam("enrolment[0].name", "HMRC-CUS-ORG")
      .formParam("enrolment[0].taxIdentifier[0].name", "EORINumber")
      .formParam("enrolment[0].taxIdentifier[0].value", "GB123456789123")
      .formParam("enrolment[0].state", "Activated")
      .check(status.is(SEE_OTHER.code()))
      .check(header("Location").is(redirectionUrl))
      .disableFollowRedirect
}
