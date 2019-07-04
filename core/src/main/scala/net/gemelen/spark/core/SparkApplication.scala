package net.gemelen.spark.core

import zio.{App, ZIO}

abstract class SparkApplication extends zio.App {

  def sparkApp: ZIO[Environment, Nothing, Int]

  override val Environment = SparkEnvironment

  import SparkApplication._
  def run(args: List[String]): ZIO[Environment, Nothing, Int] =
    sparkApp.provide(Environment).fold(
      error => ErrorReturnCode,
      success => SuccessReturnCode
    )

}

object SparkApplication {

  val SuccessReturnCode = 0
  val ErrorReturnCode = 1

}

