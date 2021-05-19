scalaVersion := "3.0.0"
crossScalaVersions := Seq(scalaVersion.value, "2.13.6", "2.12.13")

libraryDependencies += "org.typelevel" %% "cats-core" % "2.6.1"
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

scalacOptions ++= Def.setting {
  Seq(
    "-deprecation",
    "-encoding", "UTF-8",
    "-feature",
    "-language:_",
    "-unchecked",
  ) ++ {
    CrossVersion.partialVersion(scalaVersion.value) match {
      case Some((2, v)) if v >= 13 =>
        Seq(
          "-Ymacro-annotations"
        )
      case Some((3, _)) =>
        Seq(
          "-no-indent",
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
}.value
