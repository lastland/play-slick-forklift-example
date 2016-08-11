// uncomment this to enable snapshot versions
//resolvers += Resolver.sonatypeRepo("snapshots")

lazy val slickVersion = "3.1.1"

libraryDependencies ++= List(
  "com.typesafe.slick" %% "slick" % slickVersion
  ,"com.typesafe.slick" %% "slick-hikaricp" % slickVersion
  ,"com.typesafe.slick" %% "slick-codegen" % slickVersion
  ,"org.scala-lang" % "scala-compiler" % "2.11.8"
  ,"com.h2database" % "h2" % "1.4.192"
  ,"com.liyaos" %% "scala-forklift-slick" % "0.2.3"
)
