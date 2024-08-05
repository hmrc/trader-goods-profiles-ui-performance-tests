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
import uk.gov.hmrc.perftests.tgp.Setup.randomAlphaNumericString
import uk.gov.hmrc.perftests.tgp.TgpRequests._

class TgpSimulation extends PerformanceTestRunner {
  val Yes = true
  val No  = false

  val createTGPProfile: Seq[HttpRequestBuilder] =
    Seq(
      getAuthWizardPage,
      postAuthWizardPageProfileSetup("${userEori}"),
      getTGPProfilePage,
      postTGPProfilePage,
      getUkimsNumberPage,
      postUkimsNumberPage,
      getNirmsQuestionPage,
      postNirmsQuestionPage(Yes),
      getNirmsNumberPage,
      postNirmsNumberPage,
      getNiphlQuestionPage,
      postNiphlQuestionPage(Yes),
      getNiphlNumberPage,
      postNiphlNumberPage,
      getProfileSetupCYAPage,
      postProfileSetupCYAPage,
      getHomePage
    )

  val createRecord: Seq[HttpRequestBuilder] =
    Seq(
      getAuthWizardPage,
      postAuthWizardPageHome("${userEori}"),
      getHomePage,
      getCreatingAGoodsRecordPage,
      postCreatingAGoodsRecordPage,
      getTraderReferencePage,
      postTraderReferencePage(randomAlphaNumericString),
      getGoodsDescriptionQuestionPage,
      postGoodsDescriptionQuestionPage(Yes),
      getCountryOfOriginPage,
      postCountryOfOriginPage("GB"),
      getCommodityCodePage,
      postCommodityCodePage(Yes, "170490"),
      getCommodityCodeResultPage("1704900000"),
      postCommodityCodeResultPage,
      getCreateRecordCYAPage,
      postCreateRecordCYAPage
    )

  val categorisationCategory1GoodAndRequestAdvice: Seq[HttpRequestBuilder] =
    Seq(
      getAuthWizardPage,
      postAuthWizardPageHome("${userEori}"),
      getHomePage,
      getGoodsRecordPageWithId("fdd243d4-03e5-4936-a0de-2d88fb0439ce"),
      getCategorisationStartPage,
      postCategorisationStartPage,
      getCategoryAssessmentPage("1"),
      postCategoryAssessmentPage("1", Yes),
      getCategoryAssessmentPage("2"),
      postCategoryAssessmentPage("2", No),
      getCyaCategorisationPage,
      postCyaCategorisationPage,
      getCategoryResultPage("category-1"),
      getGoodsRecordPageWithId("fdd243d4-03e5-4936-a0de-2d88fb0439ce"),
      getAdviceStartPage,
      postAdviceStartPage,
      getAskNamePage,
      postAskNamePage,
      getAskEmailPage,
      postAskEmailPage,
      getAdviceCYAPage,
      postAdviceCYAPage,
      getAdviceSuccessPage,
      getSignOutPage
    )

  val categorisationCategory2Good: Seq[HttpRequestBuilder] =
    Seq(
      getAuthWizardPage,
      postAuthWizardPageHome("${userEori}"),
      getHomePage,
      getGoodsRecordPageWithId("3dcd34a4-6a5d-4730-bc7a-92618b132c50"),
      getCategorisationStartPage,
      postCategorisationStartPage,
      getCategoryAssessmentPage("1"),
      postCategoryAssessmentPage("1", Yes),
      getCategoryAssessmentPage("2"),
      postCategoryAssessmentPage("2", Yes),
      getCategoryAssessmentPage("3"),
      postCategoryAssessmentPage("3", No),
      getLongerCommodityCodePage,
      postLongerCommodityCodePage("99"),
      getLongerCommodityCodeResultPage("1704909900"),
      postLongerCommodityCodeResultPage,
      getSupplementaryQuestionPage,
      postSupplementaryQuestionPage,
      getSupplementaryUnitPage,
      postSupplementaryUnitPage("11"),
      getCyaCategorisationPage,
      postCyaCategorisationPage,
      getCategoryResultPage("category-2"),
      getSignOutPage
    )

  val categorisationCategory3Good: Seq[HttpRequestBuilder] =
    Seq(
      getAuthWizardPage,
      postAuthWizardPageHome("${userEori}"),
      getHomePage,
      getGoodsRecordPageWithId("38c33c85-6a8a-4cac-a381-2f17cffbb24f"),
      getCategorisationStartPage,
      postCategorisationStartPage,
      getCategoryAssessmentPage("1"),
      postCategoryAssessmentPage("1", Yes),
      getCategoryAssessmentPage("2"),
      postCategoryAssessmentPage("2", Yes),
      getCategoryAssessmentPage("3"),
      postCategoryAssessmentPage("3", Yes),
      getCategoryAssessmentPage("4"),
      postCategoryAssessmentPage("4", Yes),
      getCategoryAssessmentPage("5"),
      postCategoryAssessmentPage("5", Yes),
      getCategoryAssessmentPage("6"),
      postCategoryAssessmentPage("6", Yes),
      getCategoryAssessmentPage("7"),
      postCategoryAssessmentPage("7", Yes),
      getCategoryAssessmentPage("8"),
      postCategoryAssessmentPage("8", Yes),
      getCyaCategorisationPage,
      postCyaCategorisationPage,
      getCategoryResultPage("standard"),
      getSignOutPage
    )

  val maintainProfile: Seq[HttpRequestBuilder] =
    Seq(
      getAuthWizardPage,
      postAuthWizardPageHome("${userEori}"),
      getHomePage,
      getProfileInformationPage,
      getUpdateUKIMSNumberPage,
      postUpdateUKIMSNumberPage("XIUKIM47699357400020231115081801"),
      getUpdateNirmsQuestionPage,
      postUpdateNirmsQuestionPage(Yes),
      getUpdateNirmsNumberPage,
      postUpdateNirmsNumberPage("RMS-GB-123456"),
      getUpdateNiphlQuestionPage,
      postUpdateNiphlQuestionPage(Yes),
      getUpdateNiphlNumberPage,
      postUpdateNiphlNumberPage("612345")
    )

  val deleteRecord: Seq[HttpRequestBuilder] =
    Seq(
      getHomePage,
      getGoodsRecordPageWithId("38c33c85-6a8a-4cac-a381-2f17cffbb24f"),
      getRemoveGoodsRecordPage,
      postRemoveGoodsRecordPage(Yes),
      getSignOutPage
    )

  setup("test-prep", "Prepare for test") withActions (Setup.setupSession: _*)

  setup("MaintainTGPProfile", "Maintain TGP Profile")
    .withRequests(maintainProfile: _*)

  setup("DeleteTGPRecord", "Delete a TGP record")
    .withRequests(deleteRecord: _*)

  setup("createTGPProfile", "Create TGP Profile")
    .withRequests(createTGPProfile: _*)

  setup("createRecord", "Create a Record")
    .withRequests(createRecord: _*)

  setup(
    "categorisationCategory2Good",
    "Categorisation Category 2 With Longer Commodity Code and Supplementary Unit"
  )
    .withRequests(categorisationCategory2Good: _*)

  setup("categorisationCategory3Good", "Categorisation Category 3 Goods Journey")
    .withRequests(categorisationCategory3Good: _*)

  setup(
    "categorisationCategory1GoodAndRequestAdvice",
    "Categorisation Category 1 Goods Journey and Request Advice Journey"
  )
    .withRequests(categorisationCategory1GoodAndRequestAdvice: _*)

  runSimulation()

}
