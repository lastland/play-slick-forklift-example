name := "scala-forklift-project"

addCommandAlias("mgm", "migration_manager/run")

addCommandAlias("mg", "migrations/run")

lazy val slickVersion = "3.1.1"

lazy val forkliftVersion = "0.2.3"

lazy val commonSettings = Seq(
  version := "1.0",
  scalaVersion := "2.11.8",
  scalacOptions += "-deprecation",
  scalacOptions += "-feature",
  resolvers += Resolver.bintrayRepo("naftoligug", "maven")
)

lazy val loggingDependencies = List(
  "org.slf4j" % "slf4j-nop" % "1.6.4" // <- disables logging
)

lazy val slickDependencies = List(
  "com.typesafe.slick" %% "slick" % "3.1.1"
)

lazy val dbDependencies = List(
  "com.typesafe.slick" %% "slick-hikaricp" % slickVersion
  ,"com.h2database" % "h2" % "1.4.192"
)

lazy val forkliftDependencies = List(
  "com.liyaos" %% "scala-forklift-slick" % forkliftVersion
)

lazy val migrationsDependencies =
  dbDependencies ++ forkliftDependencies ++ loggingDependencies

lazy val migrationManagerDependencies = dbDependencies ++ forkliftDependencies

lazy val generatedCodeDependencies = slickDependencies

lazy val migrationManager = Project("migration_manager",
  file("migration_manager")).settings(
  commonSettings:_*).settings {
  libraryDependencies ++= migrationManagerDependencies
}

lazy val migrations = Project("migrations",
  file("migrations")).dependsOn(
  generatedCode, migrationManager).settings(
  commonSettings:_*).settings {
  libraryDependencies ++= migrationsDependencies
}

lazy val generatedCode = Project("generate_code",
  file("generated_code")).settings(commonSettings:_*).settings {
  libraryDependencies ++= generatedCodeDependencies
}
