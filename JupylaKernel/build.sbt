// lazy val root = (project in file(".")).
//   setting(
//     name := "JupylaKernel"
//     version := "1.0"
//     scalaVersion := "2.11.8"
//     mainClass in Compile := Some("JupylaKernel")
//   )
// import AssemblyKeys._
// assemblySettings
name := "JupylaKernel"
version := "1.0"
scalaVersion := "2.11.8"

mainClass in assembly := Some("JupylaKernel")
// mainClass in Compile := Some("com.jupyla.JupylaKernel")
// mainClass in (Compile, run) := Some("com.jupyla.JupylaKernel")
// mainClass in (Compile, packageBin) := Some("com.jupyla.JupylaKernel")

resolvers += "Sonatype (releases)" at "https://oss.sonatype.org/content/repositories/releases/"
resolvers += "RoundEights" at "http://maven.spikemark.net/roundeights"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.0" % "test",
  "org.xerial" % "sqlite-jdbc" % "3.8.7",
  "com.typesafe.slick" %% "slick" % "3.1.1",
  "org.slf4j" % "slf4j-nop" % "1.6.4",
//  "org.zeromq" % "zeromq-scala-binding_2.11.0-M3" % "0.0.7",
  "org.json4s" %% "json4s-native" % "3.5.0",
//  "com.mdialog" %% "scala-zeromq" % "1.2.0",
//  "org.zeromq" % "jzmq" % "3.1.0",
  "com.roundeights" %% "hasher" % "1.2.0",
  "org.zeromq" % "zeromq-scala-binding_2.10.0-RC5" % "0.0.9",
//  "org.zeromq" % "jeromq" % "0.3.4",
  "com.typesafe.play" %% "play-json" % "2.4.0-M1"

//  "net.java.dev.jna" %  "jna"           % "3.0.9",
//  "com.github.jnr"   %  "jnr-constants" % "0.8.2",
//  "org.scalatest"    %  "scalatest_2.10"     % "2.0.M5b" % "test"
)
