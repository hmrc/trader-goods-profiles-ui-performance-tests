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
      getHomePage,
      getCreatingAGoodsRecordPage,
      postCreatingAGoodsRecordPage,
      getProductReferencePage,
      postProductReferencePage(randomAlphaNumericString),
      getGoodsDescriptionPage,
      postGoodsDescriptionPage(randomAlphaNumericString),
      getCountryOfOriginPage,
      postCountryOfOriginPage("GB"),
      getCommodityCodePage,
      postCommodityCodePage(Yes, "170490"),
      getCommodityCodeResultPage("170490"),
      postCommodityCodeResultPage,
      getCreateRecordCYAPage,
      postCreateRecordCYAPage
    )

  val categorisationCategory1GoodAndRequestAdvice: Seq[HttpRequestBuilder] =
    Seq(
      getHomePage,
      getGoodsRecordPageWithId("fdd243d4-03e5-4936-a0de-2d88fb0439ce"),
      getCategorisationPreparationPage,
      getCategorisationStartPage,
      postCategorisationStartPage,
      getCategoryAssessmentPage("1"),
      postCategoryAssessmentPage("1", Array("4061")),
      getCategoryAssessmentPage("2"),
      postCategoryAssessmentPage("2", Array("none")),
      getCyaCategorisationPage("9301900000"),
      postCyaCategorisationPage,
      getCategoryResultPage("Category 1", "category-1"),
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
      getCategorisationPreparationPage,
      getCategorisationStartPage,
      postCategorisationStartPage,
      getCategoryAssessmentPage("1"),
      postCategoryAssessmentPage("1", Array("Y997")),
      getCategoryAssessmentPage("2"),
      postCategoryAssessmentPage("2", Array("Y984")),
      getCategoryAssessmentPage("3"),
      postCategoryAssessmentPage("3", Array("none")),
      getLongerCommodityCodePage,
      postLongerCommodityCodePage("9912"),
      getLongerCommodityCodeResultPage("1704909912"),
      postLongerCommodityCodeResultPage,
      getSupplementaryQuestionPage,
      postSupplementaryQuestionPage,
      getSupplementaryUnitPage,
      postSupplementaryUnitPage("11"),
      getCyaCategorisationPage(""),
      postCyaCategorisationPage,
      getCategoryResultPage("Category 2", "category-2"),
      getSignOutPage
    )

  val categorisationCategory3Good: Seq[HttpRequestBuilder] =
    Seq(
      getAuthWizardPage,
      postAuthWizardPageHome("${userEori}"),
      getHomePage,
      getGoodsRecordPageWithId("38c33c85-6a8a-4cac-a381-2f17cffbb24f"),
      getCategorisationPreparationPage,
      getCategorisationStartPage,
      postCategorisationStartPage,
      getCategoryAssessmentPage("1"),
      postCategoryAssessmentPage("1", Array("Y949")),
      getCategoryAssessmentPage("2"),
      postCategoryAssessmentPage("2", Array("Y920")),
      getCategoryAssessmentPage("3"),
      postCategoryAssessmentPage("3", Array("Y957")),
      getCategoryAssessmentPage("4"),
      postCategoryAssessmentPage("4", Array("Y920")),
      getCategoryAssessmentPage("5"),
      postCategoryAssessmentPage("5", Array("Y997")),
      getCategoryAssessmentPage("6"),
      postCategoryAssessmentPage("6", Array("Y984")),
      getCategoryAssessmentPage("7"),
      postCategoryAssessmentPage("7", Array("Y069")),
      getCategoryAssessmentPage("8"),
      postCategoryAssessmentPage("8", Array("Y923")),
      getCyaCategorisationPage("3602000090"),
      postCyaCategorisationPage,
      getCategoryResultPage("Standard goods", "standard"),
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

  val updateRecord: Seq[HttpRequestBuilder] =
    Seq(
      getHomePage,
      getGoodsRecordPageWithId("3dcd34a4-6a5d-4730-bc7a-92618b132c50"),
      getUpdateProductReferencePage,
      postUpdateProductReferencePage(randomAlphaNumericString),
      getUpdateProductReferenceCYAPage,
      postUpdateProductReferenceCYAPage,
      getChangeGoodsDescriptionPage,
      postChangeGoodsDescriptionPage(Yes),
      getUpdateGoodsDescriptionPage,
      postUpdateGoodsDescriptionPage("Organic Apples"),
      getUpdateGoodsDescriptionCYAPage,
      postUpdateGoodsDescriptionCYAPage,
      getChangeCountryOfOriginPage,
      postChangeCountryOfOriginPage(Yes),
      getUpdateCountryOfOriginPage,
      postUpdateCountryOfOriginPage("GB"),
      getUpdateCountryOfOriginCYAPage,
      postUpdateCountryOfOriginCYAPage,
      getGoodsRecordPageWithId("3dcd34a4-6a5d-4730-bc7a-92618b132c50"),
      getChangeCommodityCodePage,
      postChangeCommodityCodePage(Yes),
      getUpdateCommodityCodePage,
      postUpdateCommodityCodePage("170490"),
      getUpdateCommodityCodeResultPage("170490"),
      postUpdateCommodityCodeResultPage,
      getUpdatedCommodityCodeSuccessPage
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

  setup("updateRecord", "Update a Record")
    .withRequests(updateRecord: _*)

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
