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

import org.mongodb.scala.MongoClient

import scala.concurrent.Await
import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

trait DropDatabase {

  private lazy val mongoClient: MongoClient = MongoClient()

  def dropCollections(): Unit = {
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
}
