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

import io.gatling.core.Predef._
import io.gatling.core.action.builder.ActionBuilder
import org.scalacheck.Gen

import java.net.URLEncoder
import java.util.concurrent.atomic.AtomicInteger

object Setup {

  private val Counter = new AtomicInteger(1234567891)

  private def eoriGenerator(counter: Int): Gen[String] = Gen.frequency(
    20 -> Gen.delay(Gen.const(nextEori(counter)))
  )

  def randomAlphaNumericString: String = {
    val chars     = ('a' to 'z') ++ ('A' to 'Z') ++ ('0' to '9')
    val reference = List.fill(15)(chars(util.Random.nextInt(chars.length))).mkString("")
    URLEncoder.encode(reference, "UTF-8")
  }

  private def nextEori(counter: Int): String =
    f"GB$counter%012d"

  private def setupSession(eori: String, session: Session): Session =
    session.setAll(
      List(
        "reference" -> randomAlphaNumericString,
        "userEori"  -> eori
      )
    )

  /** Add a random reference and EORI to the session to be used by requests in the simulation set on the session through
    * session.set or session.setAll accessed from session by $${userEori}
    */
  val setupSession: List[ActionBuilder] =
    exec { (session: Session) =>
      // Incrementing unique EORIs
      val nextId = Counter.incrementAndGet()
      val eori   = eoriGenerator(nextId).sample.get
      println(eori)
      setupSession(eori, session)
    }.actionBuilders
}
