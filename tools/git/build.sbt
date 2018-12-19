// uncomment this to enable snapshot versions
//resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies ++= List(
  "com.liyaos" %% "scala-forklift-slick" % "[0.3,0.4)"
  , "com.liyaos" %% "scala-forklift-git-tools" % "[0.3,0.4)"
  , "com.typesafe" % "config" % "[1.3,1.4)"
  , "org.eclipse.jgit" % "org.eclipse.jgit" % "5.2.0.201812061821-r"
)
