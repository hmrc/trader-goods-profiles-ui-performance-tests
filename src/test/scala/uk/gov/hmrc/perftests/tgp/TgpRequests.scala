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
import uk.gov.hmrc.perftests.tgp.Requests._

object TgpRequests extends Configuration {

  implicit class BooleanOps(b: Boolean) {
    def toPayload: Map[String, String] = if (b) Map("value" -> "true") else Map("value" -> "false")
  }

  def getTGPProfilePage: HttpRequestBuilder =
    getPage(
      "Setting up your profile",
      saveToken = true,
      url = s"$tgpUrl/trader-goods-profiles/create-profile/start"
    )

  def postTGPProfilePage: HttpRequestBuilder =
    postPage(
      "Create Profile Start Page",
      s"$tgpUrl/trader-goods-profiles/create-profile/start",
      Map.empty[String, String]
    )

  def getUkimsNumberPage: HttpRequestBuilder =
    getPage(
      "UK Internal Market Scheme",
      saveToken = true,
      url = s"$tgpUrl/trader-goods-profiles/create-profile/ukims-number"
    )

  def postUkimsNumberPage: HttpRequestBuilder = {
    val enterUkims = Map(
      "value" -> "XIUKIM47699357400020231115081800"
    )
    postPage(
      "enter your UKIMS number",
      s"$tgpUrl/trader-goods-profiles/create-profile/ukims-number",
      enterUkims
    )
  }

  def getNirmsQuestionPage: HttpRequestBuilder =
    getPage(
      "Northern Ireland Retail Movement Scheme",
      saveToken = true,
      url = s"$tgpUrl/trader-goods-profiles/create-profile/nirms-question"
    )

  def postNirmsQuestionPage(answer: Boolean): HttpRequestBuilder =
    postPage(
      "Click Yes or No on NIRMS Question Page",
      s"$tgpUrl/trader-goods-profiles/create-profile/nirms-question",
      answer.toPayload
    )

  def getNirmsNumberPage: HttpRequestBuilder =
    getPage(
      "What is your NIRMS number?",
      saveToken = true,
      url = s"$tgpUrl/trader-goods-profiles/create-profile/nirms-number"
    )

  def postNirmsNumberPage: HttpRequestBuilder = {
    val enterNirms = Map(
      "value" -> "RMS-GB-123456"
    )
    postPage(
      "enter your NIRMS number",
      s"$tgpUrl/trader-goods-profiles/create-profile/nirms-number",
      enterNirms
    )
  }

  def getNiphlQuestionPage: HttpRequestBuilder =
    getPage(
      "Northern Ireland plant health label",
      saveToken = true,
      url = s"$tgpUrl/trader-goods-profiles/create-profile/niphl-question"
    )

  def postNiphlQuestionPage(answer: Boolean): HttpRequestBuilder =
    postPage(
      "Click Yes or No on NIPHL Question Page",
      s"$tgpUrl/trader-goods-profiles/create-profile/niphl-question",
      answer.toPayload
    )

  def getNiphlNumberPage: HttpRequestBuilder =
    getPage(
      "NIPHL registration number",
      saveToken = true,
      url = s"$tgpUrl/trader-goods-profiles/create-profile/niphl-number"
    )

  def postNiphlNumberPage: HttpRequestBuilder = {
    val enterNiphl = Map(
      "value" -> "SN12345"
    )
    postPage(
      "enter your NIPHL number",
      s"$tgpUrl/trader-goods-profiles/create-profile/niphl-number",
      enterNiphl
    )
  }

  def getProfileSetupCYAPage: HttpRequestBuilder =
    getPage(
      "Check your answers",
      saveToken = true,
      url = s"$tgpUrl/trader-goods-profiles/create-profile/check-your-answers"
    )

  def postProfileSetupCYAPage: HttpRequestBuilder =
    postPage(
      "Check your answers page",
      s"$tgpUrl/trader-goods-profiles/create-profile/check-your-answers",
      Map.empty[String, String]
    )

  def getHomePage: HttpRequestBuilder =
    getPage(
      "Trader Goods Profile homepage",
      s"$tgpUrl/trader-goods-profiles/homepage"
    )

  def getCreatingAGoodsRecordPage: HttpRequestBuilder =
    getPage(
      "Creating a goods record",
      saveToken = true,
      url = s"$tgpUrl/trader-goods-profiles/create-record/start"
    )

  def postCreatingAGoodsRecordPage: HttpRequestBuilder =
    postPage(
      "Create a goods record",
      s"$tgpUrl/trader-goods-profiles/create-record/start",
      Map.empty[String, String]
    )

  def getTraderReferencePage: HttpRequestBuilder =
    getPage(
      "Trader reference",
      saveToken = true,
      url = s"$tgpUrl/trader-goods-profiles/create-record/trader-reference"
    )

  def postTraderReferencePage(ref: String): HttpRequestBuilder =
    postPage(
      "enter your Trader Reference",
      s"$tgpUrl/trader-goods-profiles/create-record/trader-reference",
      Map("value" -> ref)
    )

  def getGoodsDescriptionQuestionPage: HttpRequestBuilder =
    getPage(
      "Goods description",
      saveToken = true,
      url = s"$tgpUrl/trader-goods-profiles/create-record/goods-description-question"
    )

  def postGoodsDescriptionQuestionPage(answer: Boolean): HttpRequestBuilder =
    postPage(
      "Click Yes or No on Goods Description Question Page",
      s"$tgpUrl/trader-goods-profiles/create-record/goods-description-question",
      answer.toPayload
    )

  def getGoodsDescriptionPage: HttpRequestBuilder =
    getPage(
      "Goods description",
      saveToken = true,
      url = s"$tgpUrl/trader-goods-profiles/create-record/goods-description"
    )

  def postGoodsDescriptionPage: HttpRequestBuilder = {
    val enterGoodsDescription = Map(
      "value" -> "Goods"
    )
    postPage(
      "enter your Goods Description",
      s"$tgpUrl/trader-goods-profiles/create-record/goods-description",
      enterGoodsDescription
    )
  }

  def getCountryOfOriginPage: HttpRequestBuilder =
    getPage(
      "Country of origin",
      saveToken = true,
      url = s"$tgpUrl/trader-goods-profiles/create-record/country-of-origin"
    )

  def postCountryOfOriginPage(countryCode: String): HttpRequestBuilder =
    postPage(
      "enter the Country Of Origin",
      s"$tgpUrl/trader-goods-profiles/create-record/country-of-origin",
      Map("value" -> countryCode)
    )

  def getCommodityCodePage: HttpRequestBuilder =
    getPage(
      "Commodity code",
      saveToken = true,
      url = s"$tgpUrl/trader-goods-profiles/create-record/commodity-code"
    )

  def postCommodityCodePage(valid: Boolean, commodityCode: String): HttpRequestBuilder =
    if (valid) {
      postPage(
        "enter your Commodity Code",
        s"$tgpUrl/trader-goods-profiles/create-record/commodity-code",
        Map("value" -> commodityCode)
      )
    } else {
      postErrorPage(
        "enter your Commodity Code",
        s"$tgpUrl/trader-goods-profiles/create-record/commodity-code",
        Map("value" -> commodityCode)
      )
    }

  def getCommodityCodeResultPage(commodityCode: String): HttpRequestBuilder =
    getPage(
      "Results for " + commodityCode,
      saveToken = true,
      url = s"$tgpUrl/trader-goods-profiles/create-record/commodity-code-result"
    )

  def postCommodityCodeResultPage: HttpRequestBuilder =
    postPage(
      "Click Yes on Commodity Code Result Page",
      s"$tgpUrl/trader-goods-profiles/create-record/commodity-code-result",
      true.toPayload
    )

  def getCreateRecordCYAPage: HttpRequestBuilder =
    getPage(
      "Check your answers",
      saveToken = true,
      url = s"$tgpUrl/trader-goods-profiles/create-record/check-your-answers"
    )

  def postCreateRecordCYAPage: HttpRequestBuilder =
    postPageAndExtractRecordId(
      "Check your answers page",
      s"$tgpUrl/trader-goods-profiles/create-record/check-your-answers",
      "/success",
      Map.empty[String, String]
    )

  def getCreateRecordSuccessPage: HttpRequestBuilder =
    getPage(
      "created a goods record",
      s"$tgpUrl/trader-goods-profiles/create-record/$${recordId}/success"
    )

  def getCategorisationStartPage: HttpRequestBuilder =
    getPage(
      "Categorisation",
      saveToken = true,
      url = s"$tgpUrl/trader-goods-profiles/update-record/$${recordId}/categorisation/start"
    )

  def postCategorisationStartPage: HttpRequestBuilder =
    postPage(
      "Categorisation",
      s"$tgpUrl/trader-goods-profiles/update-record/$${recordId}/categorisation/start",
      Map.empty[String, String]
    )

  def getCategoryAssessmentPage(categoryNumber: String): HttpRequestBuilder =
    getPage(
      "Category assessment " + categoryNumber,
      saveToken = true,
      url = s"$tgpUrl/trader-goods-profiles/update-record/$${recordId}/categorisation/category-assessment/" + (Integer
        .parseInt(categoryNumber.trim) - 1)
    )

  def postCategoryAssessmentPage(categoryNumber: String, answer: Boolean): HttpRequestBuilder =
    postPage(
      "Category assessment " + categoryNumber,
      s"$tgpUrl/trader-goods-profiles/update-record/$${recordId}/categorisation/category-assessment/" + (Integer
        .parseInt(categoryNumber.trim) - 1),
      answer.toPayload
    )

  def getLongerCommodityCodePage: HttpRequestBuilder =
    getPage(
      "You need to enter a longer commodity code",
      saveToken = true,
      url = s"$tgpUrl/trader-goods-profiles/update-record/$${recordId}/categorisation/longer-commodity-code"
    )

  def postLongerCommodityCodePage(longerCode: String): HttpRequestBuilder =
    postPage(
      "Enter Longer Commodity Code",
      s"$tgpUrl/trader-goods-profiles/update-record/$${recordId}/categorisation/longer-commodity-code",
      Map("value" -> longerCode)
    )

  def getLongerCommodityCodeResultPage(longerCommodityCode: String): HttpRequestBuilder =
    getPage(
      "Results for " + longerCommodityCode,
      saveToken = true,
      url = s"$tgpUrl/trader-goods-profiles/update-record/$${recordId}/categorisation/longer-commodity-code-result"
    )

  def postLongerCommodityCodeResultPage: HttpRequestBuilder =
    postPage(
      "Click Yes on Longer Commodity Code Result Page",
      s"$tgpUrl/trader-goods-profiles/update-record/$${recordId}/categorisation/longer-commodity-code-result",
      true.toPayload
    )

  def getSupplementaryQuestionPage: HttpRequestBuilder =
    getPage(
      "Supplementary unit",
      saveToken = true,
      url =
        s"$tgpUrl/trader-goods-profiles/update-record/$${recordId}/categorisation/supplementary-unit-question/create"
    )

  def postSupplementaryQuestionPage: HttpRequestBuilder =
    postPage(
      "Click Yes on Supplementary unit Page",
      s"$tgpUrl/trader-goods-profiles/update-record/$${recordId}/categorisation/supplementary-unit-question/create",
      true.toPayload
    )

  def getSupplementaryUnitPage: HttpRequestBuilder =
    getPage(
      "",
      saveToken = true,
      url = s"$tgpUrl/trader-goods-profiles/update-record/$${recordId}/categorisation/supplementary-unit-amount/create"
    )

  def postSupplementaryUnitPage(Unit: String): HttpRequestBuilder =
    postPage(
      "Enter Supplementary unit",
      s"$tgpUrl/trader-goods-profiles/update-record/$${recordId}/categorisation/supplementary-unit-amount/create",
      Map("value" -> Unit)
    )

  def getCyaCategorisationPage: HttpRequestBuilder =
    getPage(
      "Check your answers",
      saveToken = true,
      url = s"$tgpUrl/trader-goods-profiles/update-record/$${recordId}/categorisation/check-your-answers"
    )

  def postCyaCategorisationPage: HttpRequestBuilder =
    postPage(
      "Check your answers",
      s"$tgpUrl/trader-goods-profiles/update-record/$${recordId}/categorisation/check-your-answers",
      Map.empty[String, String]
    )

  def getCategoryResultPage(category: String): HttpRequestBuilder =
    getPage(
      "Categorisation complete",
      s"$tgpUrl/trader-goods-profiles/update-record/$${recordId}/categorisation/result/" + category
    )

  def getSignOutPage: HttpRequestBuilder =
    getPage(
      "You have now signed out",
      s"$tgpUrl/trader-goods-profiles/signed-out"
    )

  def getGoodsProfilePage: HttpRequestBuilder =
    getPage(
      "Goods profile",
      saveToken = true,
      url = s"""$tgpUrl/trader-goods-profiles/goods-profile?page=1"""
    )

  def postGoodsProfilePage(traderReference: String): HttpRequestBuilder =
    postPage(
      "Goods profile",
      s"""$tgpUrl/trader-goods-profiles/goods-profile?page=1""",
      Map("value" -> traderReference)
    )

  def getGoodsRecordPage: HttpRequestBuilder =
    getPage(
      "Goods record",
      s"$tgpUrl/trader-goods-profiles/goods-record/$${recordId}"
    )

  def getGoodsRecordPageWithId(RecordId: String): HttpRequestBuilder =
    getPage(
      "Goods record",
      s"$tgpUrl/trader-goods-profiles/goods-record/$RecordId",
      saveId = true
    )

  def getRemoveGoodsRecordPage: HttpRequestBuilder =
    getPage(
      "Removing goods record",
      s"$tgpUrl/trader-goods-profiles/remove-record/$${recordId}/goods-record"
    )

  def postRemoveGoodsRecordPage(answer: Boolean): HttpRequestBuilder =
    postPage(
      "Removing goods record",
      s"$tgpUrl/trader-goods-profiles/remove-record/$${recordId}/goods-record",
      answer.toPayload
    )

  def getAdviceStartPage: HttpRequestBuilder =
    getPage(
      "Asking HMRC for advice",
      saveToken = true,
      url = s"$tgpUrl/trader-goods-profiles/update-record/$${recordId}/create-advice/start"
    )

  def postAdviceStartPage: HttpRequestBuilder =
    postPage(
      "Asking HMRC for advice",
      s"$tgpUrl/trader-goods-profiles/update-record/$${recordId}/create-advice/start",
      Map.empty[String, String]
    )

  def getAskNamePage: HttpRequestBuilder =
    getPage(
      "What is your name?",
      saveToken = true,
      url = s"$tgpUrl/trader-goods-profiles/update-record/$${recordId}/create-advice/name"
    )

  def postAskNamePage: HttpRequestBuilder = {
    val enterName = Map(
      "value" -> "TestFirstName TestLastName"
    )
    postPage(
      "What is your name?",
      s"$tgpUrl/trader-goods-profiles/update-record/$${recordId}/create-advice/name",
      enterName
    )
  }

  def getAskEmailPage: HttpRequestBuilder =
    getPage(
      "What is your email address?",
      saveToken = true,
      url = s"$tgpUrl/trader-goods-profiles/update-record/$${recordId}/create-advice/email"
    )

  def postAskEmailPage: HttpRequestBuilder = {
    val enterEmail = Map(
      "value" -> "Test@test.com"
    )
    postPage(
      "What is your email address?",
      s"$tgpUrl/trader-goods-profiles/update-record/$${recordId}/create-advice/email",
      enterEmail
    )
  }

  def getAdviceCYAPage: HttpRequestBuilder =
    getPage(
      "Check your answers before sending your request for advice",
      saveToken = true,
      url = s"$tgpUrl/trader-goods-profiles/update-record/$${recordId}/create-advice/check-your-answers"
    )

  def postAdviceCYAPage: HttpRequestBuilder =
    postPage(
      "Advice Check your answers",
      s"$tgpUrl/trader-goods-profiles/update-record/$${recordId}/create-advice/check-your-answers",
      Map.empty[String, String]
    )

  def getAdviceSuccessPage: HttpRequestBuilder =
    getPage(
      "Request for advice complete",
      s"$tgpUrl/trader-goods-profiles/update-record/$${recordId}/create-advice/success"
    )

  /** ** Maintain Profile ***
    */
  def getProfileInformationPage: HttpRequestBuilder =
    getPage(
      "Profile details",
      s"$tgpUrl/trader-goods-profiles/update-profile/profile-information"
    )

  def getUpdateUKIMSNumberPage: HttpRequestBuilder =
    getPage(
      "UK Internal Market Scheme",
      saveToken = true,
      url = s"$tgpUrl/trader-goods-profiles/update-profile/ukims-number"
    )

  def postUpdateUKIMSNumberPage(UkimsNumber: String): HttpRequestBuilder =
    postPage(
      "enter your UKIMS number",
      s"$tgpUrl/trader-goods-profiles/update-profile/ukims-number",
      Map("value" -> UkimsNumber)
    )

  def getUpdateNirmsQuestionPage: HttpRequestBuilder =
    getPage(
      "Northern Ireland Retail Movement Scheme",
      saveToken = true,
      url = s"$tgpUrl/trader-goods-profiles/update-profile/nirms-question"
    )

  def postUpdateNirmsQuestionPage(answer: Boolean): HttpRequestBuilder =
    postPage(
      "Click Yes or No on NIRMS Question Page",
      s"$tgpUrl/trader-goods-profiles/update-profile/nirms-question",
      answer.toPayload
    )

  def getUpdateNirmsNumberPage: HttpRequestBuilder =
    getPage(
      "What is your NIRMS number?",
      saveToken = true,
      url = s"$tgpUrl/trader-goods-profiles/update-profile/nirms-number"
    )

  def postUpdateNirmsNumberPage(nirmsNumber: String): HttpRequestBuilder =
    postPage(
      "enter your NIRMS number",
      s"$tgpUrl/trader-goods-profiles/update-profile/nirms-number",
      Map("value" -> nirmsNumber)
    )

  def getUpdateNiphlQuestionPage: HttpRequestBuilder =
    getPage(
      "Northern Ireland plant health label",
      saveToken = true,
      url = s"$tgpUrl/trader-goods-profiles/update-profile/niphl-question"
    )

  def postUpdateNiphlQuestionPage(answer: Boolean): HttpRequestBuilder =
    postPage(
      "Click Yes or No on NIPHL Question Page",
      s"$tgpUrl/trader-goods-profiles/update-profile/niphl-question",
      answer.toPayload
    )

  def getUpdateNiphlNumberPage: HttpRequestBuilder =
    getPage(
      "NIPHL registration number",
      saveToken = true,
      url = s"$tgpUrl/trader-goods-profiles/update-profile/niphl-number"
    )

  def postUpdateNiphlNumberPage(niphlNumber: String): HttpRequestBuilder =
    postPage(
      "enter your NIPHL number",
      s"$tgpUrl/trader-goods-profiles/update-profile/niphl-number",
      Map("value" -> niphlNumber)
    )
}
