name := "scala-forklift-project"

addCommandAlias("mgm", "migration_manager/run")

addCommandAlias("mg", "migrations/run")

lazy val forkliftVersion = "[0.3,0.4)"
lazy val playSlickVersion = "[3.0,3.1)"
lazy val slickVersion = "[3.2,3.3)"

lazy val commonSettings = Seq(
  version := "1.0",
  scalaVersion := "2.12.7",
  scalacOptions += "-deprecation",
  scalacOptions += "-feature",
  resolvers += Resolver.bintrayRepo("naftoligug", "maven")
)

lazy val loggingDependencies = List(
  "org.slf4j" % "slf4j-nop" % "1.6.4" // <- disables logging
)

lazy val slickDependencies = List(
  "com.typesafe.slick" %% "slick" % slickVersion
)

lazy val dbDependencies = List(
  "com.typesafe.slick" %% "slick-hikaricp" % slickVersion
  , "com.h2database" % "h2" % "[1.3,1.4)"
)

lazy val forkliftDependencies = List(
  "com.liyaos" %% "scala-forklift-slick" % forkliftVersion
)

lazy val migrationsDependencies =
  dbDependencies ++ forkliftDependencies ++ loggingDependencies

lazy val migrationManagerDependencies = dbDependencies ++ forkliftDependencies

lazy val generatedCodeDependencies = slickDependencies

lazy val migrationManager = (project in file("migration_manager"))
  .settings(
    commonSettings,
    libraryDependencies ++= migrationManagerDependencies
  )

lazy val migrations = (project in file("migrations")).dependsOn(
  generatedCode, migrationManager)
  .settings(
    commonSettings,
    libraryDependencies ++= migrationsDependencies
  )

lazy val generatedCode = (project in file("generated_code"))
  .settings(
    commonSettings,
    libraryDependencies ++= generatedCodeDependencies
  )

lazy val app = (project in file("app"))
  .enablePlugins(PlayScala)
  .dependsOn(generatedCode)
  .settings(
    libraryDependencies ++= Seq(
      ehcache,
      guice,
      ws,
      "com.typesafe.play" %% "play-slick" % playSlickVersion,
      "com.typesafe.play" %% "play-slick-evolutions" % playSlickVersion,
      specs2 % Test
    ) ++ dbDependencies
  )

lazy val root = (project in file("."))
  .settings(commonSettings)
  .aggregate(generatedCode, migrations, migrationManager, app)
