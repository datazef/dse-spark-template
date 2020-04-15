lazy val root = (project in file(".")).

  settings(
    inThisBuild(List(
      organization := "$organization$",
      scalaVersion := "2.11.8"
    )),
    name := "$name$",
    version := "$version$",
    javacOptions ++= Seq("-source", "1.8", "-target", "1.8"),
    javaOptions ++= Seq("-Xms512M", "-Xmx2048M", "-XX:MaxPermSize=2048M", "-XX:+CMSClassUnloadingEnabled"),
    scalacOptions ++= Seq("-deprecation", "-unchecked"),
    parallelExecution in Test := false,
    fork := true,
    coverageHighlighting := true,
    libraryDependencies ++= Seq(
      "com.datastax.dse" % "dse-spark-dependencies" % "$dseVersion$" % "provided" exclude ("org.slf4j", "log4j-over-slf4j"),
      "org.scalatest" %% "scalatest" % "3.1.1" % Test,
      "org.scalacheck" %% "scalacheck" % "1.14.3" % Test,
      "com.holdenkarau" %% "spark-testing-base" % "$sparkTestingbaseRelease$" % Test
    ),

    // uses compile classpath for the run task, including "provided" jar (cf http://stackoverflow.com/a/21803413/3827)
    run in Compile := Defaults.runTask(fullClasspath in Compile, mainClass in (Compile, run), runner in (Compile, run)).evaluated,

    scalacOptions ++= Seq("-deprecation", "-unchecked"),
    pomIncludeRepository := { x => false },

   resolvers ++= Seq(
      "sonatype-releases" at "https://oss.sonatype.org/content/repositories/releases/",
      "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/",
      "Second Typesafe repo" at "http://repo.typesafe.com/typesafe/maven-releases/",
      "DataStax-Repo" at "https://repo.datastax.com/public-repos/",
      Resolver.sonatypeRepo("public")
    ),
  )
