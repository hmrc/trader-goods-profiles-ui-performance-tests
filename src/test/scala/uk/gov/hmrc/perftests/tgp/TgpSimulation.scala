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
import uk.gov.hmrc.perftests.tgp.AuthRequests.{getAuthWizardPage, postAuthWizardPageHome, postAuthWizardPageProfileSetup}
import uk.gov.hmrc.perftests.tgp.TgpRequests._

class TgpSimulation extends PerformanceTestRunner {

  val profileSetupJourneyWithNirmsAndNiphl: Seq[HttpRequestBuilder] =
    Seq(
      getAuthWizardPage,
      postAuthWizardPageProfileSetup,
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
      getProfileSetupCYAPage,
      postProfileSetupCYAPage,
      getHomePage
    )

  val profileSetupJourneyWithoutNirmsAndNiphl: Seq[HttpRequestBuilder] =
    Seq(
      getAuthWizardPage,
      postAuthWizardPageProfileSetup,
      getTGPProfilePage,
      postTGPProfilePage,
      getUkimsNumberPage,
      postUkimsNumberPage,
      getNirmsQuestionPage,
      postNirmsQuestionPage(false),
      getNiphlQuestionPage,
      postNiphlQuestionPage(false),
      getProfileSetupCYAPage,
      postProfileSetupCYAPage,
      getHomePage
    )

  val profileSetupJourneyWithoutNirmsAndWithNiphl: Seq[HttpRequestBuilder] =
    Seq(
      getAuthWizardPage,
      postAuthWizardPageProfileSetup,
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
      getProfileSetupCYAPage,
      postProfileSetupCYAPage,
      getHomePage
    )

  val profileSetupJourneyWithNirmsAndWithoutNiphl: Seq[HttpRequestBuilder] =
    Seq(
      getAuthWizardPage,
      postAuthWizardPageProfileSetup,
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
      getProfileSetupCYAPage,
      postProfileSetupCYAPage,
      getHomePage
    )

  val createRecordJourneyWithGoodsReference: Seq[HttpRequestBuilder] =
    Seq(
      getAuthWizardPage,
      postAuthWizardPageHome,
      getHomePage,
      postHomePage,
      getCreatingAGoodsRecordPage,
      postCreatingAGoodsRecordPage,
      getTraderReferencePage,
      postTraderReferencePage,
      getGoodsDescriptionQuestionPage,
      postGoodsDescriptionQuestionPage(false),
      getGoodsDescriptionPage,
      postGoodsDescriptionPage,
      getCountryOfOriginPage,
      postCountryOfOriginPage,
      getCommodityCodePage,
      postCommodityCodePage(true),
      getCommodityCodeResultPage,
      postCommodityCodeResultPage,
      getCreateRecordCYAPage,
      postCreateRecordCYAPage,
      getHomePage
    )

  val createRecordJourneyWithoutGoodsReference: Seq[HttpRequestBuilder] =
    Seq(
      getAuthWizardPage,
      postAuthWizardPageHome,
      getHomePage,
      postHomePage,
      getCreatingAGoodsRecordPage,
      postCreatingAGoodsRecordPage,
      getTraderReferencePage,
      postTraderReferencePage,
      getGoodsDescriptionQuestionPage,
      postGoodsDescriptionQuestionPage(true),
      getCountryOfOriginPage,
      postCountryOfOriginPage,
      getCommodityCodePage,
      postCommodityCodePage(true),
      getCommodityCodeResultPage,
      postCommodityCodeResultPage,
      getCreateRecordCYAPage,
      postCreateRecordCYAPage,
      getHomePage
    )

  val createRecordJourneyWithGoodsReferenceIncorrectCode: Seq[HttpRequestBuilder] =
    Seq(
      getAuthWizardPage,
      postAuthWizardPageHome,
      getHomePage,
      postHomePage,
      getCreatingAGoodsRecordPage,
      postCreatingAGoodsRecordPage,
      getTraderReferencePage,
      postTraderReferencePage,
      getGoodsDescriptionQuestionPage,
      postGoodsDescriptionQuestionPage(false),
      getGoodsDescriptionPage,
      postGoodsDescriptionPage,
      getCountryOfOriginPage,
      postCountryOfOriginPage,
      getCommodityCodePage,
      postCommodityCodePage(false),
    )

  val createRecordJourneyWithoutGoodsReferenceIncorrectCode: Seq[HttpRequestBuilder] =
    Seq(
      getAuthWizardPage,
      postAuthWizardPageHome,
      getHomePage,
      postHomePage,
      getCreatingAGoodsRecordPage,
      postCreatingAGoodsRecordPage,
      getTraderReferencePage,
      postTraderReferencePage,
      getGoodsDescriptionQuestionPage,
      postGoodsDescriptionQuestionPage(true),
      getCountryOfOriginPage,
      postCountryOfOriginPage,
      getCommodityCodePage,
      postCommodityCodePage(false),
    )

  setup("WithNirmsAndNiphl", "Profile Setup Journey With NIRMS And NIPHL")
    .withRequests(profileSetupJourneyWithNirmsAndNiphl: _*)

  setup("WithoutNirmsAndNiphl", "Profile Setup Journey Without NIRMS And NIPHL")
    .withRequests(profileSetupJourneyWithoutNirmsAndNiphl: _*)

  setup("WithoutNirmsAndWithNiphl", "Profile Setup Journey Without NIRMS And With NIPHL")
    .withRequests(profileSetupJourneyWithoutNirmsAndWithNiphl: _*)

  setup("WithNirmsAndWithoutNiphl", "Profile Setup Journey With NIRMS And Without NIPHL")
    .withRequests(profileSetupJourneyWithNirmsAndWithoutNiphl: _*)

  setup("WithGoodsDescription", "Create Record Journey With Goods Description")
    .withRequests(createRecordJourneyWithGoodsReference: _*)

  setup("WithoutGoodsDescription", "Create Record Journey Without Goods Description")
    .withRequests(createRecordJourneyWithoutGoodsReference: _*)

  setup("WithGoodsDescriptionIncorrectCode", "Create Record Journey With Goods Description and incorrect Commodity Code")
    .withRequests(createRecordJourneyWithGoodsReferenceIncorrectCode: _*)

  setup("WithoutGoodsDescriptionIncorrectCode", "Create Record Journey Without Goods Description and incorrect Commodity Code")
    .withRequests(createRecordJourneyWithoutGoodsReferenceIncorrectCode: _*)

  runSimulation()

}
