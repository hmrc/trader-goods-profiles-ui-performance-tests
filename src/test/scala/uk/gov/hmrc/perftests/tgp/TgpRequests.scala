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
import uk.gov.hmrc.perftests.tgp.Requests.{getPage, postPage}

object TgpRequests extends Configuration {

  implicit class BooleanOps(b: Boolean) {
    def toPayload: Map[String, String] = if (b) Map("value" -> "true") else Map("value" -> "false")
  }

  def getTGPProfilePage: HttpRequestBuilder =
    getPage(
      "Setting up your profile",
      saveToken = true,
      s"$tgpUrl/trader-goods-profiles/profile-setup"
    )

  def postTGPProfilePage: HttpRequestBuilder =
    postPage(
      "TGP Profile Page",
      s"$tgpUrl/trader-goods-profiles/profile-setup",
      Map.empty[String, String]
    )

  def getUkimsNumberPage: HttpRequestBuilder =
    getPage(
      "UK Internal Market Scheme number",
      saveToken = true,
      s"$tgpUrl/trader-goods-profiles/ukims-number"
    )

  def postUkimsNumberPage: HttpRequestBuilder = {
    val enterUkims = Map(
      "value" -> "XIUKIM47699357400020231115081800"
    )
    postPage(
      "enter your UKIMS number",
      s"$tgpUrl/trader-goods-profiles/ukims-number",
      enterUkims
    )
  }

  def getNirmsQuestionPage: HttpRequestBuilder =
    getPage(
      "Northern Ireland Retail Movement Scheme",
      saveToken = true,
      s"$tgpUrl/trader-goods-profiles/nirms-question"
    )

  def postNirmsQuestionPage(answer: Boolean): HttpRequestBuilder =
    postPage(
      "Click Yes or No on NIRMS Question Page",
      s"$tgpUrl/trader-goods-profiles/nirms-question",
      answer.toPayload
    )

  def getNirmsNumberPage: HttpRequestBuilder =
    getPage(
      "What is your NIRMS number?",
      saveToken = true,
      s"$tgpUrl/trader-goods-profiles/nirms-number"
    )

  def postNirmsNumberPage: HttpRequestBuilder = {
    val enterNirms = Map(
      "value" -> "RMS-GB-123456"
    )
    postPage(
      "enter your NIRMS number",
      s"$tgpUrl/trader-goods-profiles/nirms-number",
      enterNirms
    )
  }

  def getNiphlQuestionPage: HttpRequestBuilder =
    getPage(
      "Northern Ireland plant health label",
      saveToken = true,
      s"$tgpUrl/trader-goods-profiles/niphl-question"
    )

  def postNiphlQuestionPage(answer: Boolean): HttpRequestBuilder =
    postPage(
      "Click Yes or No on NIPHL Question Page",
      s"$tgpUrl/trader-goods-profiles/niphl-question",
      answer.toPayload
    )

  def getNiphlNumberPage: HttpRequestBuilder =
    getPage(
      "NIPHL registration number",
      saveToken = true,
      s"$tgpUrl/trader-goods-profiles/niphl-number"
    )

  def postNiphlNumberPage: HttpRequestBuilder = {
    val enterNiphl = Map(
      "value" -> "SN12345"
    )
    postPage(
      "enter your NIPHL number",
      s"$tgpUrl/trader-goods-profiles/niphl-number",
      enterNiphl
    )
  }

  def getProfileSetupCYAPage: HttpRequestBuilder =
    getPage(
      "Check your answers",
      saveToken = true,
      s"$tgpUrl/trader-goods-profiles/cya-nirms-niphls"
    )

  def postProfileSetupCYAPage: HttpRequestBuilder =
    postPage(
      "Check your answers page",
      s"$tgpUrl/trader-goods-profiles/cya-nirms-niphls",
      Map.empty[String, String]
    )

  def getHomePage: HttpRequestBuilder =
    getPage(
      "Trader Goods Profile homepage",
      s"$tgpUrl/trader-goods-profiles/homepage"
    )

  def postHomePage: HttpRequestBuilder =
    postPage(
      "start create record process",
      s"$tgpUrl/trader-goods-profiles/homepage",
      Map.empty[String, String]
    )

  def getCreatingAGoodsRecordPage: HttpRequestBuilder =
    getPage(
      "Creating a goods record",
      saveToken = true,
      s"$tgpUrl/trader-goods-profiles/create-record/create-record-start"
    )

  def postCreatingAGoodsRecordPage: HttpRequestBuilder =
    postPage(
      "Create a goods record",
      s"$tgpUrl/trader-goods-profiles/create-record/create-record-start",
      Map.empty[String, String]
    )

  def getTraderReferencePage: HttpRequestBuilder =
    getPage(
      "Trader reference",
      saveToken = true,
      s"$tgpUrl/trader-goods-profiles/trader-reference"
    )

  def postTraderReferencePage: HttpRequestBuilder = {
    val enterTraderReference = Map(
      "value" -> "trader"
    )
    postPage(
      "enter your Trader Reference",
      s"$tgpUrl/trader-goods-profiles/create-record/trader-reference",
      enterTraderReference
    )
  }

  def getGoodsDescriptionQuestionPage: HttpRequestBuilder =
    getPage(
      "Goods description",
      saveToken = true,
      s"$tgpUrl/trader-goods-profiles/create-record/goods-description-question"
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
      s"$tgpUrl/trader-goods-profiles/create-record/gods-description"
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
      s"$tgpUrl/trader-goods-profiles/country-of-origin"
    )

  def postCountryOfOriginPage: HttpRequestBuilder = {
    val enterCountryOfOrigin = Map(
      "value" -> "UK"
    )
    postPage(
      "enter the Country Of Origin",
      s"$tgpUrl/trader-goods-profiles/country-of-origin",
      enterCountryOfOrigin
    )
  }

  def getCommodityCodePage: HttpRequestBuilder =
    getPage(
      "Commodity code",
      saveToken = true,
      s"$tgpUrl/trader-goods-profiles/create-record/commodity-code"
    )

  def postCommodityCodePage: HttpRequestBuilder = {
    val enterCommodityCode = Map(
      "value" -> "1234567890"
    )
    postPage(
      "enter your Commodity Code",
      s"$tgpUrl/trader-goods-profiles/create-record/commodity-code",
      enterCommodityCode
    )
  }

  def getCommodityCodeResultPage: HttpRequestBuilder =
    getPage(
      "Results for 0702000007",
      saveToken = true,
      s"$tgpUrl/trader-goods-profiles/create-record/commodity-code-result"
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
      s"$tgpUrl/trader-goods-profiles/create-record/cya-create-record"
    )

  def postCreateRecordCYAPage: HttpRequestBuilder =
    postPage(
      "Check your answers page",
      s"$tgpUrl/trader-goods-profiles/create-record/cya-create-record",
      Map.empty[String, String]
    )
}
