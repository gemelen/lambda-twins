import compiler._
import deps._

ThisBuild / scalaVersion := "2.12.9"
ThisBuild / version := "0.1"
ThisBuild / turbo := true
ThisBuild / scalacOptions := CompilerOpts.scalacFlags

lazy val root = project
  .in(file("."))
  .enablePlugins(RootProjectPlugin)
  .aggregate(
    kit,
    processing,
    core,
    batch,
    streaming
  )

lazy val core = project
  .in(file("core"))
  .dependsOn(kit)
  .dependsOn(processing)
  .settings(
    name := "core",
    scalacOptions -= "-Ywarn-dead-code",
    libraryDependencies ++=
      Seq(
        avro,
        zio
      ) ++
      spark ++
      jackson
  )

// Collection of external functionality,
// wrapped with ZIO.
lazy val kit = project
  .in(file("kit"))
  .settings(
    name := "kit",
    libraryDependencies ++=
      Seq(
        kafka,
        loggingFacade,
        typesafeConfig,
        zio
      ) ++
      consul
  )

// Data processing api.
// Externalize into separate lib in a real project.
lazy val processing = project
  .in(file("processing"))
  .settings(
    name := "processing",
    libraryDependencies ++=
      Seq(
        loggingFacade
      ) ++
      cats
  )

// Spark batch processing application
lazy val batch = project
  .in(file("batch"))
  .dependsOn(core)
  .settings(
    name := "batch",
    libraryDependencies ++=
      Seq(
        typesafeConfig,
        zio
      ) ++
      spark ++
      loggingFacility
  )

// Spark Streaming processing application
lazy val streaming = project
  .in(file("streaming"))
  .dependsOn(core)
  .settings(
    name := "streaming",
    libraryDependencies ++=
      loggingFacility
  )

