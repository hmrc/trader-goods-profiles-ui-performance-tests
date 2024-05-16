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

  val fullJourney: Seq[HttpRequestBuilder] =
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

  setup("full-journey", "profile setup")
    .withRequests(fullJourney: _*)

  runSimulation()

}
