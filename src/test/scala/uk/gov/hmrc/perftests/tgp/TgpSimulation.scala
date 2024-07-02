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

  val createRecordJourneyWithGoodsReferenceCategory2Goods: Seq[HttpRequestBuilder] =
    Seq(
      getAuthWizardPage,
      postAuthWizardPageHome("GB123456789097"),
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
      postCommodityCodePage(true, "1601001011"),
      getCommodityCodeResultPage("1601001011"),
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
      getCyaCategorisationPage,
      postCyaCategorisationPage,
      getCategoryResultPage("category-2")
    )

  val createRecordJourneyWithoutGoodsReferenceCategory3Goods: Seq[HttpRequestBuilder] =
    Seq(
      getAuthWizardPage,
      postAuthWizardPageHome("GB123456789098"),
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
      postCategoryAssessmentPage("2", "8440"),
      getCategoryAssessmentPage("3"),
      postCategoryAssessmentPage("3", "Y957"),
      getCategoryAssessmentPage("4"),
      postCategoryAssessmentPage("4", "Y920"),
      getCategoryAssessmentPage("5"),
      postCategoryAssessmentPage("5", "Y997"),
      getCategoryAssessmentPage("6"),
      postCategoryAssessmentPage("6", "Y984"),
      getCyaCategorisationPage,
      postCyaCategorisationPage,
      getCategoryResultPage("standard")
    )

  val createRecordJourneyWithoutGoodsReferenceCategory1Goods: Seq[HttpRequestBuilder] =
    Seq(
      getAuthWizardPage,
      postAuthWizardPageHome("GB123456789098"),
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
      getCategoryResultPage("category-1")
    )

  val createRecordJourneyWithGoodsReferenceIncorrectCode: Seq[HttpRequestBuilder] =
    Seq(
      getAuthWizardPage,
      postAuthWizardPageHome("GB123456789097"),
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
      postCommodityCodePage(false, "0702000001")
    )

  val createRecordJourneyWithoutGoodsReferenceIncorrectCode: Seq[HttpRequestBuilder] =
    Seq(
      getAuthWizardPage,
      postAuthWizardPageHome("GB123456789097"),
      getHomePage,
      getCreatingAGoodsRecordPage,
      postCreatingAGoodsRecordPage,
      getTraderReferencePage,
      postTraderReferencePage,
      getGoodsDescriptionQuestionPage,
      postGoodsDescriptionQuestionPage(true),
      getCountryOfOriginPage,
      postCountryOfOriginPage("GB"),
      getCommodityCodePage,
      postCommodityCodePage(false, "0702000001")
    )

  val accreditationJourney: Seq[HttpRequestBuilder] =
    Seq(
      getAuthWizardPage,
      postAuthWizardPageHome("GB123456789097"),
      getHomePage,
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

  setup("WithNirmsAndNiphl", "Profile Setup Journey With NIRMS And NIPHL")
    .withRequests(profileSetupJourneyWithNirmsAndNiphl: _*)

  setup("WithoutNirmsAndNiphl", "Profile Setup Journey Without NIRMS And NIPHL")
    .withRequests(profileSetupJourneyWithoutNirmsAndNiphl: _*)

  setup("WithoutNirmsAndWithNiphl", "Profile Setup Journey Without NIRMS And With NIPHL")
    .withRequests(profileSetupJourneyWithoutNirmsAndWithNiphl: _*)

  setup("WithNirmsAndWithoutNiphl", "Profile Setup Journey With NIRMS And Without NIPHL")
    .withRequests(profileSetupJourneyWithNirmsAndWithoutNiphl: _*)

  setup("WithGoodsDescriptionCategory2", "Create Record Journey With Goods Description Category 2")
    .withRequests(createRecordJourneyWithGoodsReferenceCategory2Goods: _*)

  setup("WithoutGoodsDescriptionCategory3", "Create Record Journey Without Goods Description Category 3")
    .withRequests(createRecordJourneyWithoutGoodsReferenceCategory3Goods: _*)

  setup("WithoutGoodsDescriptionCategory1", "Create Record Journey Without Goods Description Category 1")
    .withRequests(createRecordJourneyWithoutGoodsReferenceCategory1Goods: _*)

  setup(
    "WithGoodsDescriptionIncorrectCode",
    "Create Record Journey With Goods Description and incorrect Commodity Code"
  )
    .withRequests(createRecordJourneyWithGoodsReferenceIncorrectCode: _*)

  setup(
    "WithoutGoodsDescriptionIncorrectCode",
    "Create Record Journey Without Goods Description and incorrect Commodity Code"
  )
    .withRequests(createRecordJourneyWithoutGoodsReferenceIncorrectCode: _*)

  setup("AccreditationJourney", "Accreditation Journey")
    .withRequests(accreditationJourney: _*)

  runSimulation()

}
