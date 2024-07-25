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
import uk.gov.hmrc.perftests.tgp.AuthRequests.{dropProfilesCollection, getAuthWizardPage, postAuthWizardPageHome, postAuthWizardPageProfileSetup}
import uk.gov.hmrc.perftests.tgp.TgpRequests._

class TgpSimulation extends PerformanceTestRunner {
  val Yes = true
  val No  = false

  val profileSetupJourneyWithUKIMSNIRMSAndNIPHL: Seq[HttpRequestBuilder] =
    Seq(
//      dropProfilesCollection,
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

  val createRecordCategory2LongerCommodityCodSupplementaryUnit: Seq[HttpRequestBuilder] =
    Seq(
      getAuthWizardPage,
      postAuthWizardPageHome("GB123456789123"),
      getHomePage,
      getCreatingAGoodsRecordPage,
      postCreatingAGoodsRecordPage,
      getTraderReferencePage,
      postTraderReferencePage("Category2${reference}"),
      getGoodsDescriptionQuestionPage,
      postGoodsDescriptionQuestionPage(Yes),
      getCountryOfOriginPage,
      postCountryOfOriginPage("GB"),
      getCommodityCodePage,
      postCommodityCodePage(Yes, "170490"),
      getCommodityCodeResultPage("1704900000"),
      postCommodityCodeResultPage,
      getCreateRecordCYAPage,
      postCreateRecordCYAPage,
      getCreateRecordSuccessPage,
      getHomePage,
      getGoodsProfilePage,
      getGoodsRecordPage,
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
      getCategoryResultPage("category-2")
    )

  val createRecordCategory3Goods: Seq[HttpRequestBuilder] =
    Seq(
      getAuthWizardPage,
      postAuthWizardPageHome("GB123456789098"),
      getHomePage,
      getCreatingAGoodsRecordPage,
      postCreatingAGoodsRecordPage,
      getTraderReferencePage,
      postTraderReferencePage("Category3${reference}"),
      getGoodsDescriptionQuestionPage,
      postGoodsDescriptionQuestionPage(Yes),
      getCountryOfOriginPage,
      postCountryOfOriginPage("IQ"),
      getCommodityCodePage,
      postCommodityCodePage(Yes, "3602000090"),
      getCommodityCodeResultPage("3602000090"),
      postCommodityCodeResultPage,
      getCreateRecordCYAPage,
      postCreateRecordCYAPage,
      getCreateRecordSuccessPage,
      getHomePage,
      getGoodsProfilePage,
      getGoodsRecordPage,
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
      getCategoryResultPage("standard")
    )

  val createRecordCategory1Goods: Seq[HttpRequestBuilder] =
    Seq(
      getAuthWizardPage,
      postAuthWizardPageHome("GB123456789099"),
      getHomePage,
      getCreatingAGoodsRecordPage,
      postCreatingAGoodsRecordPage,
      getTraderReferencePage,
      postTraderReferencePage("Category1${reference}"),
      getGoodsDescriptionQuestionPage,
      postGoodsDescriptionQuestionPage(Yes),
      getCountryOfOriginPage,
      postCountryOfOriginPage("IQ"),
      getCommodityCodePage,
      postCommodityCodePage(Yes, "9301900000"),
      getCommodityCodeResultPage("9301900000"),
      postCommodityCodeResultPage,
      getCreateRecordCYAPage,
      postCreateRecordCYAPage,
      getCreateRecordSuccessPage,
      getHomePage,
      getGoodsProfilePage,
      getGoodsRecordPage,
      getCategorisationStartPage,
      postCategorisationStartPage,
      getCategoryAssessmentPage("1"),
      postCategoryAssessmentPage("1", Yes),
      getCategoryAssessmentPage("2"),
      postCategoryAssessmentPage("2", No),
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

  setup("test-prep", "Prepare for test") withActions (Setup.setupSession: _*)

  setup("CreateTGPProfile", "TGP Profile Setup Journey With UKIMS, NIRMS And NIPHL")
    .withRequests(profileSetupJourneyWithUKIMSNIRMSAndNIPHL: _*)

  setup("Category2GoodsJourney", "Create Record, Category 2 With Longer Commodity Code and Supplementary Unit")
    .withRequests(createRecordCategory2LongerCommodityCodSupplementaryUnit: _*)

  setup("Category3GoodsJourney", "Create Record, Category 3 Goods Journey")
    .withRequests(createRecordCategory3Goods: _*)

  setup("Category1GoodsRequestAdviceJourney", "Create Record, Category 1 Goods Journey and Request Advice Journey")
    .withRequests(createRecordCategory1Goods: _*)

  runSimulation()

}
