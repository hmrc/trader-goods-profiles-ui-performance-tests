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

  def profileSetupJourneyWithUKIMSNIRMSAndNIPHL: Seq[HttpRequestBuilder] =
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

  val createRecordCategory2LongerCommodityCodSupplementaryUnit: Seq[HttpRequestBuilder] =
    Seq(
      getAuthWizardPage,
      postAuthWizardPageHome("GB123456789123"),
      getHomePage,
      getCreatingAGoodsRecordPage,
      postCreatingAGoodsRecordPage,
      getTraderReferencePage,
      postTraderReferencePage,
      getGoodsDescriptionQuestionPage,
      postGoodsDescriptionQuestionPage(false),
      getGoodsDescriptionPage,
      postGoodsDescriptionPage,
      getCountryOfOriginPage,
      postCountryOfOriginPage("GB"),
      getCommodityCodePage,
      postCommodityCodePage(true, "170490"),
      getCommodityCodeResultPage("1704900000"),
      postCommodityCodeResultPage,
      getCreateRecordCYAPage,
      postCreateRecordCYAPage,
      getCreateRecordSuccessPage,
      getCategorisationStartPage,
      postCategorisationStartPage,
      getCategoryAssessmentPage("1"),
      postCategoryAssessmentPage("1", "Y997"),
      getCategoryAssessmentPage("2"),
      postCategoryAssessmentPage("2", "Y984"),
      getCategoryAssessmentPage("3"),
      postCategoryAssessmentPage("3", "none"),
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
      getCategoryResultPage("category-2")
    )

  val createRecordCategory3Goods: Seq[HttpRequestBuilder] =
    Seq(
      getAuthWizardPage,
      postAuthWizardPageHome("GB123456789123"),
      getHomePage,
      getCreatingAGoodsRecordPage,
      postCreatingAGoodsRecordPage,
      getTraderReferencePage,
      postTraderReferencePage,
      getGoodsDescriptionQuestionPage,
      postGoodsDescriptionQuestionPage(true),
      getCountryOfOriginPage,
      postCountryOfOriginPage("IQ"),
      getCommodityCodePage,
      postCommodityCodePage(true, "3602000090"),
      getCommodityCodeResultPage("3602000090"),
      postCommodityCodeResultPage,
      getCreateRecordCYAPage,
      postCreateRecordCYAPage,
      getCreateRecordSuccessPage,
      getCategorisationStartPage,
      postCategorisationStartPage,
      getCategoryAssessmentPage("1"),
      postCategoryAssessmentPage("1", "Y949"),
      getCategoryAssessmentPage("2"),
      postCategoryAssessmentPage("2", "Y920"),
      getCategoryAssessmentPage("3"),
      postCategoryAssessmentPage("3", "Y957"),
      getCategoryAssessmentPage("4"),
      postCategoryAssessmentPage("4", "Y920"),
      getCategoryAssessmentPage("5"),
      postCategoryAssessmentPage("5", "Y997"),
      getCategoryAssessmentPage("6"),
      postCategoryAssessmentPage("6", "Y984"),
      getCategoryAssessmentPage("7"),
      postCategoryAssessmentPage("7", "Y949"),
      getCategoryAssessmentPage("8"),
      postCategoryAssessmentPage("8", "Y923"),
      getCyaCategorisationPage,
      postCyaCategorisationPage,
      getCategoryResultPage("standard")
    )

  val createRecordCategory1Goods: Seq[HttpRequestBuilder] =
    Seq(
      getAuthWizardPage,
      postAuthWizardPageHome("GB123456789123"),
      getHomePage,
      getCreatingAGoodsRecordPage,
      postCreatingAGoodsRecordPage,
      getTraderReferencePage,
      postTraderReferencePage,
      getGoodsDescriptionQuestionPage,
      postGoodsDescriptionQuestionPage(true),
      getCountryOfOriginPage,
      postCountryOfOriginPage("IQ"),
      getCommodityCodePage,
      postCommodityCodePage(true, "9301900000"),
      getCommodityCodeResultPage("9301900000"),
      postCommodityCodeResultPage,
      getCreateRecordCYAPage,
      postCreateRecordCYAPage,
      getCreateRecordSuccessPage,
      getCategorisationStartPage,
      postCategorisationStartPage,
      getCategoryAssessmentPage("1"),
      postCategoryAssessmentPage("1", "8392"),
      getCategoryAssessmentPage("2"),
      postCategoryAssessmentPage("2", "none"),
      getCyaCategorisationPage,
      postCyaCategorisationPage,
      getCategoryResultPage("category-1"),
      getHomePage,
      getGoodsProfilePage,
      getGoodsRecordPage,
      getAdviceStartPage,
      postAdviceStartPage,
      getAskNamePage,
      postAskNamePage,
      getAskEmailPage,
      postAskEmailPage,
      getAdviceCYAPage,
      postAdviceCYAPage,
      getAdviceSuccessPage
    )

  setup("CreateTGPProfile", "TGP Profile Setup Journey With UKIMS, NIRMS And NIPHL")
    .withRequests(profileSetupJourneyWithUKIMSNIRMSAndNIPHL: _*)

  setup("Category2GoodsJourney", "Create Record, Category 2 With Longer Commodity Code and Supplementary Unit")
    .withRequests(createRecordCategory2LongerCommodityCodSupplementaryUnit: _*)

  setup("Category3GoodsJourney", "Create Record, Category 3 Goods Journey")
    .withRequests(createRecordCategory3Goods: _*)

  setup("Category1GoodsRequestAdviceJourney", "Create Record, Category 1 Goods Journey and Request Advice Journey")
    .withRequests(createRecordCategory1Goods: _*)

  /*before {
    dropCollections()
  }*/
  runSimulation()
  /*after {
    dropCollections()
  }*/
}
