package net.gemelen.spark.core

import org.apache.spark.sql.SparkSession
import zio.{App, ZIO}

abstract class SparkApplication extends zio.App {

  val sparkRuntime = new SparkRuntime {}
  def sparkApp: ZIO[SparkRuntime, Nothing, Int]

  import SparkApplication._
  def run(args: List[String]): ZIO[Environment, Nothing, Int] =
    sparkApp
      .provide(sparkRuntime)
      .fold(
        error => ErrorReturnCode,
        success => SuccessReturnCode
      )

}

object SparkApplication {

  val SuccessReturnCode = 0
  val ErrorReturnCode = 1

}

