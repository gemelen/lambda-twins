import deps._

ThisBuild / scalaVersion := "2.12.8"
ThisBuild / version := "0.1"
ThisBuild / turbo := true

resolvers += Resolver.sonatypeRepo("snapshots")

lazy val root = project
  .in(file("."))
  .enablePlugins(RootProjectPlugin)
  .settings(
  )
  .aggregate(
    core,
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
        scalaLogging,
        typesafeConfig,
        zio
      ) ++
      spark
  )

lazy val batch = project
  .in(file("batch"))
  .enablePlugins(
  )
  .dependsOn(core)
  .settings(
    name := "batch",
    libraryDependencies ++=
      Seq(
        zio
      ) ++
      spark ++
      logging
  )

lazy val streaming = project
  .in(file("streaming"))
  .enablePlugins(
  )
  .dependsOn(core)
  .settings(
    name := "streaming",
    libraryDependencies ++=
      logging
  )

