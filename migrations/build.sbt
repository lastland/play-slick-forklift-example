// uncomment this to enable snapshot versions
//resolvers += Resolver.sonatypeRepo("snapshots")

lazy val slickVersion = "3.1.1"

libraryDependencies ++= List(
  "com.liyaos" %% "scala-forklift-slick" % "0.2.3"
  ,"com.typesafe.slick" %% "slick-hikaricp" % slickVersion
  ,"com.h2database" % "h2" % "1.4.192"
)
