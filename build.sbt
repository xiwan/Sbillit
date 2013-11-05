name := "Sbillit"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  //javaJdbc,
  //javaEbean,
  //cache,
  "org.mybatis" % "mybatis" % "3.1.1",
  "mysql" % "mysql-connector-java" % "5.1.18",
  "org.springframework" % "spring-expression" % "3.2.0.RELEASE",          
  javaCore
)     

play.Project.playJavaSettings
