import play.Project._

name := "hello-play-scala"

version := "1.0-SNAPSHOT"

resolvers += "Artifactory Realm" at "http://lib.innovaccer.com:8081/artifactory/gradle-local"

libraryDependencies ++= Seq(
  "org.webjars" %% "webjars-play" % "2.2.2", 
  "org.webjars" % "bootstrap" % "2.3.1",
  "com.innovaccer.analytics" % "synthetic_patient_generator" % "0.1.0.2"
)

playScalaSettings
