package net.gemelen.spark.core

import net.gemelen.spark.core.log.Logger
import zio.Runtime
import zio.internal.{ Platform, PlatformLive }

trait SparkRuntime extends Runtime[Logger] {
  type SparkEnvironment = Logger

  val Platform: Platform = PlatformLive.Default
  val Environment: SparkEnvironment = Logger.Live
}

