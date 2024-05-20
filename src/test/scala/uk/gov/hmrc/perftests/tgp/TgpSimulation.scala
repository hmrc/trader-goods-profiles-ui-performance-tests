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
import uk.gov.hmrc.performance.simulation.PerformanceTestRunner
import uk.gov.hmrc.perftests.tgp.AuthRequests.{getAuthWizardPage, postAuthWizardPage}
import uk.gov.hmrc.perftests.tgp.TgpRequests._

class TgpSimulation extends PerformanceTestRunner {

  val profileSetupJourneyWithNirmsAndNiphl: Seq[HttpRequestBuilder] =
    Seq(
      getAuthWizardPage,
      postAuthWizardPage,
      getTGPProfilePage,
      postTGPProfilePage,
      getUkimsNumberPage,
      postUkimsNumberPage,
      getNirmsQuestionPage,
      postNirmsQuestionPage(true),
      getNirmsNumberPage,
      postNirmsNumberPage,
      getNiphlQuestionPage,
      postNiphlQuestionPage(true),
      getNiphlNumberPage,
      postNiphlNumberPage,
      getCheckYourAnswersPage,
      postCheckYourAnswersPage,
      getHomePage
    )

  val profileSetupJourneyWithoutNirmsAndNiphl: Seq[HttpRequestBuilder] =
    Seq(
      getAuthWizardPage,
      postAuthWizardPage,
      getTGPProfilePage,
      postTGPProfilePage,
      getUkimsNumberPage,
      postUkimsNumberPage,
      getNirmsQuestionPage,
      postNirmsQuestionPage(false),
      getNiphlQuestionPage,
      postNiphlQuestionPage(false),
      getCheckYourAnswersPage,
      postCheckYourAnswersPage,
      getHomePage
    )

  val profileSetupJourneyWithoutNirmsAndWithNiphl: Seq[HttpRequestBuilder] =
    Seq(
      getAuthWizardPage,
      postAuthWizardPage,
      getTGPProfilePage,
      postTGPProfilePage,
      getUkimsNumberPage,
      postUkimsNumberPage,
      getNirmsQuestionPage,
      postNirmsQuestionPage(false),
      getNiphlQuestionPage,
      postNiphlQuestionPage(true),
      getNiphlNumberPage,
      postNiphlNumberPage,
      getCheckYourAnswersPage,
      postCheckYourAnswersPage,
      getHomePage
    )

  val profileSetupJourneyWithNirmsAndWithoutNiphl: Seq[HttpRequestBuilder] =
    Seq(
      getAuthWizardPage,
      postAuthWizardPage,
      getTGPProfilePage,
      postTGPProfilePage,
      getUkimsNumberPage,
      postUkimsNumberPage,
      getNirmsQuestionPage,
      postNirmsQuestionPage(true),
      getNirmsNumberPage,
      postNirmsNumberPage,
      getNiphlQuestionPage,
      postNiphlQuestionPage(false),
      getCheckYourAnswersPage,
      postCheckYourAnswersPage,
      getHomePage
    )

  setup("WithNirmsAndNiphl", "Profile Setup Journey With NIRMS And NIPHL")
    .withRequests(profileSetupJourneyWithNirmsAndNiphl: _*)

  setup("WithoutNirmsAndNiphl", "Profile Setup Journey Without NIRMS And NIPHL")
    .withRequests(profileSetupJourneyWithoutNirmsAndNiphl: _*)

  setup("WithoutNirmsAndWithNiphl", "Profile Setup Journey Without NIRMS And With NIPHL")
    .withRequests(profileSetupJourneyWithoutNirmsAndWithNiphl: _*)

  setup("WithNirmsAndWithoutNiphl", "Profile Setup Journey With NIRMS And Without NIPHL")
    .withRequests(profileSetupJourneyWithNirmsAndWithoutNiphl: _*)

  runSimulation()

}
