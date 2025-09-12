import sbt._

object Dependencies {

  private val gatlingVersion = "3.6.1"

  private val test: Seq[ModuleID] = Seq(
    "uk.gov.hmrc"       %% "performance-test-runner" % "6.2.0",
    "org.mongodb.scala" %% "mongo-scala-driver"      % "4.9.0",
    "org.scalacheck"    %% "scalacheck"              % "1.17.0",
    "com.typesafe"       % "config"                  % "1.4.2"
  ).map(_ % Test)

  def apply(): Seq[ModuleID] = test
}
