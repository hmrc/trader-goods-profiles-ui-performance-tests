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
import org.mongodb.scala.model.{Filters, Projections}
import org.mongodb.scala.{Document, MongoClient, Observable, Observer}
import uk.gov.hmrc.perftests.tgp.Requests.{getPage, postErrorPage, postPage}

import java.util.UUID
import scala.concurrent.Await
import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

object TgpRequests extends Configuration {

  private lazy val mongoClient: MongoClient = MongoClient()
  var recordId                              = ""
  def dropCollections(): Unit               = {
    println("============================Dropping Collection")

    def dropCollection(dbName: String, collectionName: String): Unit =
      Await.result(
        mongoClient.getDatabase(dbName).getCollection(collectionName).drop().toFuture(),
        10 seconds
      )

    dropCollection("trader-goods-profiles-data-store", "profiles")
    dropCollection("trader-goods-profiles-data-store", "checkRecords")
    dropCollection("trader-goods-profiles-data-store", "goodsItemRecords")
  }

//  private def getRecordId(eoriNum: String): String = {
//    val Collection                       = mongoClient.getDatabase("trader-goods-profiles-data-store").getCollection("goodsItemRecords")
//    val observable: Observable[Document] = Collection
//      .find(Filters.equal("eori", eoriNum))
//      .projection(Projections.include("_id"))
//      .first()
//    observable.subscribe(new Observer[Document] {
//      override def onNext(result: Document): Unit = {
//        val o = result.toBsonDocument()
//        recordId = o.get("_id").asString().getValue
//        println(s"Found document with _id: $recordId")
//        // String.valueOf(result.getObjectId("_id")
//      }
//
//      override def onError(e: Throwable): Unit =
//        println(s"Failed to fetch document: ${e.getMessage}")
//
//      override def onComplete(): Unit = {
//        println("Completed fetching document")
//        mongoClient.close()
//      }
//    })
//    recordId
//  }
  implicit class BooleanOps(b: Boolean) {
    def toPayload: Map[String, String] = if (b) Map("value" -> "true") else Map("value" -> "false")
  }

  def getTGPProfilePage: HttpRequestBuilder =
    getPage(
      "Setting up your profile",
      saveToken = true,
      s"$tgpUrl/trader-goods-profiles/create-profile/start"
    )

  def postTGPProfilePage: HttpRequestBuilder =
    postPage(
      "TGP Profile Page",
      s"$tgpUrl/trader-goods-profiles/create-profile/start",
      Map.empty[String, String]
    )

  def getUkimsNumberPage: HttpRequestBuilder =
    getPage(
      """""",
      saveToken = true,
      s"$tgpUrl/trader-goods-profiles/create-profile/ukims-number"
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
      s"$tgpUrl/trader-goods-profiles/create-profile/nirms-question"
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
      s"$tgpUrl/trader-goods-profiles/create-profile/nirms-number"
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
      s"$tgpUrl/trader-goods-profiles/create-profile/niphl-question"
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
      s"$tgpUrl/trader-goods-profiles/create-profile/niphl-number"
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
      s"$tgpUrl/trader-goods-profiles/create-profile/check-your-answers"
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
      s"$tgpUrl/trader-goods-profiles/create-record/start"
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
      s"$tgpUrl/trader-goods-profiles/create-record/trader-reference"
    )

  def postTraderReferencePage: HttpRequestBuilder = {
    val traderReference      = "Trader " + UUID.randomUUID().toString
    val enterTraderReference = Map(
      "value" -> traderReference
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
      s"$tgpUrl/trader-goods-profiles/create-record/goods-description"
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
      s"$tgpUrl/trader-goods-profiles/create-record/country-of-origin"
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
      s"$tgpUrl/trader-goods-profiles/create-record/commodity-code"
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
      s"$tgpUrl/trader-goods-profiles/create-record/check-your-answers"
    )

  def postCreateRecordCYAPage: HttpRequestBuilder =
    postPage(
      "Check your answers page",
      s"$tgpUrl/trader-goods-profiles/create-record/check-your-answers",
      Map.empty[String, String]
    )

  def getCreateRecordSuccessPage: HttpRequestBuilder = {
    Thread.sleep(10000)
    val recId = getRecordId("GB123456789098")
//    println("recordID: " + recId)
    getPage(
      "created a goods record",
      s"$tgpUrl/trader-goods-profiles/create-record/" + recordId + "/success"
    )
  }

  def getCategorisationStartPage: HttpRequestBuilder = {
    print("this is the first recordId: " + recordId)

    getPage(
      "Categorisation",
      saveToken = true,
      s"$tgpUrl/trader-goods-profiles/update-record/" + recordId + "/categorisation/start"
    )
  }

  def postCategorisationStartPage: HttpRequestBuilder =
    postPage(
      "Categorisation",
      s"$tgpUrl/trader-goods-profiles/update-record/" + recordId + "/categorisation/start",
      Map.empty[String, String]
    )

  def getCategoryAssessmentPage(categoryNumber: String): HttpRequestBuilder =
    getPage(
      "Category assessment " + categoryNumber,
      saveToken = true,
      s"$tgpUrl/trader-goods-profiles/update-record/" + recordId + "/categorisation/category-assessment/" + (Integer
        .parseInt(categoryNumber.trim) - 1)
    )

  def postCategoryAssessmentPage(categoryNumber: String, conditionValue: String): HttpRequestBuilder =
    postPage(
      "Category assessment " + categoryNumber,
      s"$tgpUrl/trader-goods-profiles/update-record/" + recordId + "/categorisation/category-assessment/" + (Integer
        .parseInt(categoryNumber.trim) - 1),
      Map("value" -> conditionValue)
    )

  def getLongerCommodityCodePage: HttpRequestBuilder =
    getPage(
      "You need to enter a longer commodity code",
      saveToken = true,
      s"$tgpUrl/trader-goods-profiles/update-record/" + recordId + "/categorisation/longer-commodity-code"
    )

  def postLongerCommodityCodePage(longerCode: String): HttpRequestBuilder =
    postPage(
      "Enter Longer Commodity Code",
      s"$tgpUrl/trader-goods-profiles/update-record/" + recordId + "/categorisation/longer-commodity-code",
      Map("value" -> longerCode)
    )

  def getLongerCommodityCodeResultPage(longerCommodityCode: String): HttpRequestBuilder =
    getPage(
      "Results for " + longerCommodityCode,
      saveToken = true,
      s"$tgpUrl/trader-goods-profiles/update-record/" + recordId + "/categorisation/longer-commodity-code-result"
    )

  def postLongerCommodityCodeResultPage: HttpRequestBuilder =
    postPage(
      "Click Yes on Longer Commodity Code Result Page",
      s"$tgpUrl/trader-goods-profiles/update-record/" + recordId + "/categorisation/longer-commodity-code-result",
      true.toPayload
    )

  def getSupplementaryQuestionPage: HttpRequestBuilder =
    getPage(
      "Supplementary unit",
      saveToken = true,
      s"$tgpUrl/trader-goods-profiles/update-record/" + recordId + "/categorisation/supplementary-unit-question"
    )

  def postSupplementaryQuestionPage: HttpRequestBuilder =
    postPage(
      "Click Yes on Supplementary unit Page",
      s"$tgpUrl/trader-goods-profiles/update-record/" + recordId + "/categorisation/supplementary-unit-question",
      true.toPayload
    )

  def getSupplementaryUnitPage: HttpRequestBuilder =
    getPage(
      "What is the good's supplementary unit?",
      saveToken = true,
      s"$tgpUrl/trader-goods-profiles/update-record/" + recordId + "/categorisation/supplementary-unit-question"
    )

  def postSupplementaryUnitPage(Unit: String): HttpRequestBuilder =
    postPage(
      "Enter Supplementary unit",
      s"$tgpUrl/trader-goods-profiles/update-record/" + recordId + "/categorisation/supplementary-unit-question",
      Map("value" -> Unit)
    )

  def getCyaCategorisationPage: HttpRequestBuilder =
    getPage(
      "Check your answers",
      saveToken = true,
      s"$tgpUrl/trader-goods-profiles/update-record/" + recordId + "/categorisation/check-your-answers"
    )

  def postCyaCategorisationPage: HttpRequestBuilder =
    postPage(
      "Check your answers",
      s"$tgpUrl/trader-goods-profiles/update-record/" + recordId + "/categorisation/check-your-answers",
      Map.empty[String, String]
    )

  def getCategoryResultPage(category: String): HttpRequestBuilder =
    getPage(
      "Categorisation complete",
      s"$tgpUrl/trader-goods-profiles/update-record/" + recordId + "/categorisation/result/" + category
    )

  def getPreviousMovementRecordsPage: HttpRequestBuilder  =
    getPage(
      "Previous movement records",
      s"$tgpUrl/trader-goods-profiles/previous-movement-records"
    )
  def postPreviousMovementRecordsPage: HttpRequestBuilder =
    postPage(
      "Previous movement records",
      s"$tgpUrl/trader-goods-profiles/previous-movement-records",
      Map.empty[String, String]
    )

  def getGoodsProfilePage: HttpRequestBuilder =
    getPage(
      "Goods profile",
      saveToken = true,
      s"$tgpUrl/trader-goods-profiles/goods-profile?page=1"
    )

  def postGoodsProfilePage: HttpRequestBuilder =
    postPage(
      "Goods profile",
      s"$tgpUrl/trader-goods-profiles/goods-profile?page=1",
      Map.empty[String, String]
    )

  def getGoodsRecordPage: HttpRequestBuilder =
    getPage(
      "Goods record",
      saveToken = true,
      s"$tgpUrl/trader-goods-profiles/goods-record/" + recordId + ""
    )

  def getAdviceStartPage: HttpRequestBuilder =
    getPage(
      "Asking HMRC for advice",
      saveToken = true,
      s"$tgpUrl/trader-goods-profiles/update-record/" + recordId + "/create-advice/start"
    )

  def postAdviceStartPage: HttpRequestBuilder =
    postPage(
      "Asking HMRC for advice",
      s"$tgpUrl/trader-goods-profiles/update-record/" + recordId + "/create-advice/start",
      Map.empty[String, String]
    )

  def getAskNamePage: HttpRequestBuilder =
    getPage(
      "What is your name?",
      saveToken = true,
      s"$tgpUrl/trader-goods-profiles/update-record/" + recordId + "/create-advice/name"
    )

  def postAskNamePage: HttpRequestBuilder = {
    val enterName = Map(
      "value" -> "TestFirstName TestLastName"
    )
    postPage(
      "What is your name?",
      s"$tgpUrl/trader-goods-profiles/update-record/" + recordId + "/create-advice/name",
      enterName
    )
  }

  def getAskEmailPage: HttpRequestBuilder =
    getPage(
      "What is your email address?",
      saveToken = true,
      s"$tgpUrl/trader-goods-profiles/update-record/" + recordId + "/create-advice/email"
    )

  def postAskEmailPage: HttpRequestBuilder = {
    val enterEmail = Map(
      "value" -> "Test@test.com"
    )
    postPage(
      "What is your email address?",
      s"$tgpUrl/trader-goods-profiles/update-record/" + recordId + "/create-advice/email",
      enterEmail
    )
  }

  def getAdviceCYAPage: HttpRequestBuilder =
    getPage(
      "Check your answers before sending your request for advice",
      saveToken = true,
      s"$tgpUrl/trader-goods-profiles/update-record/" + recordId + "/create-advice/check-your-answers"
    )

  def postAdviceCYAPage: HttpRequestBuilder =
    postPage(
      "Advice Check your answers",
      s"$tgpUrl/trader-goods-profiles/update-record/" + recordId + "/create-advice/check-your-answers",
      Map.empty[String, String]
    )

  def getAdviceSuccessPage: HttpRequestBuilder =
    getPage(
      "Request for advice complete",
      s"$tgpUrl/trader-goods-profiles/update-record/" + recordId + "/create-advice/success"
    )
}
