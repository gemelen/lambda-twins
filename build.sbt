import deps._

ThisBuild / scalaVersion := "2.12.8"
ThisBuild / version := "0.1"
ThisBuild / turbo := true

resolvers += Resolver.sonatypeRepo("snapshots")
scalacOptions += "-Ypartial-unification"

lazy val root = project
  .in(file("."))
  .enablePlugins(RootProjectPlugin)
  .settings(
  )
  .aggregate(
    core,
    processing,
    batch,
    streaming
  )

lazy val core = project
  .in(file("core"))
  .enablePlugins()
  .settings(
    name := "core",
    libraryDependencies ++=
      Seq(
        loggingFacade,
        typesafeConfig,
        zio
      ) ++
      consul ++
      spark ++
      jackson
  )

// Data processing api.
// Externalize into separate lib in a real project.
lazy val processing = project
  .in(file("processing"))
  .enablePlugins()
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

