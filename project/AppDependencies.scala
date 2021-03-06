import play.core.PlayVersion
import play.sbt.PlayImport._
import sbt._

object AppDependencies {

  val compile = Seq(
    ws,
    "uk.gov.hmrc" %% "bootstrap-play-25" % "3.7.0",
    "uk.gov.hmrc" %% "domain" % "5.2.0",
    "com.eclipsesource" %% "play-json-schema-validator" % "0.8.9",
    "uk.gov.hmrc" %% "play-auditing" % "3.9.0-play-25"
  )

  def test(scope: String = "test") = Seq(
    "uk.gov.hmrc" %% "hmrctest" % "3.1.0" % scope,
    "org.scalatest" %% "scalatest" % "3.0.4" % scope,
    "org.pegdown" % "pegdown" % "1.6.0" % scope,
    "org.scalatestplus.play" %% "scalatestplus-play" % "2.0.1" % scope,
    "org.mockito" % "mockito-core" % "1.9.5",
    "com.typesafe.play" %% "play-test" % PlayVersion.current % scope,
    "com.github.tomakehurst" % "wiremock" % "2.15.0" % scope
  )

}
