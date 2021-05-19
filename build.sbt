import sbtcrossproject.CrossPlugin.autoImport.crossProject

def hash() = sys.process.Process("git rev-parse HEAD").lineStream_!.head

moduleName := "root"
effSettings
commonJvmSettings
//libraryDependencies += "org.specs2" %% "specs2-html" % specs2Version % "test"

dependsOn(
  coreJVM % "test->test;compile->compile",
)

lazy val core = crossProject(JVMPlatform).in(file("."))
  .settings(moduleName := "eff")
  .jvmSettings(commonJvmSettings)
  .settings(effSettings)

lazy val coreJVM = core.jvm

lazy val buildSettings = Seq(
  organization := "org.atnos",
  scalaVersion := "3.0.0",
  crossScalaVersions := Seq(scalaVersion.value, "2.13.6", "2.12.13")
)

lazy val commonSettings = Seq(
  libraryDependencies += "org.typelevel" %%% "cats-core" % "2.6.1",
  scalacOptions ++= commonScalacOptions.value,
  scalacOptions += "-no-indent",
  libraryDependencies ++= {
    CrossVersion.partialVersion(scalaVersion.value) match {
      case Some((3, _)) =>
        Nil
      case _ =>
        Seq(
          compilerPlugin("org.typelevel" %% "kind-projector" % "0.13.0" cross CrossVersion.full)
        )
    }
  }
)

lazy val commonJvmSettings = Seq(
//  libraryDependencies ++= specs2.value,
  Test / fork := true,
  Global / cancelable := true,
  Test / scalacOptions ~= (_.filterNot(_ == "-Xfatal-warnings")),
) ++ Seq(Test / scalacOptions ++= Seq("-Yrangepos"))

lazy val effSettings =
  buildSettings ++ commonSettings

lazy val commonScalacOptions = Def.setting {
  Seq(
    "-deprecation",
    "-encoding", "UTF-8",
    "-feature",
    "-language:_",
    "-unchecked",
//    "-Xlint",
//    "-Xlint:-nullary-unit",
//    "-Ywarn-numeric-widen",
//    "-Ywarn-value-discard"
  ) ++ {
    CrossVersion.partialVersion(scalaVersion.value) match {
      case Some((2, v)) if v >= 13 =>
        Seq(
          "-Ymacro-annotations"
        )
      case Some((3, _)) =>
        Seq(
          "-Ykind-projector"
        )
      case _ =>
        Seq(
          "-Xfatal-warnings",
          "-Yno-adapted-args",
          "-Ypartial-unification",
          "-Xfuture"
        )
    }
  }
}
