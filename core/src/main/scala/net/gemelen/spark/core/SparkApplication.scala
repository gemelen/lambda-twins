package net.gemelen.spark.core

import net.gemelen.spark.core.log.Logger
import net.gemelen.spark.core.log.Logger._
import org.apache.spark.sql.SparkSession
import zio.{App, ZIO}

abstract class SparkApplication extends zio.App {

  def sparkApp: ZIO[Logger, Nothing, Int]

  import SparkApplication._
  def run(args: List[String]): ZIO[Environment, Nothing, Int] =
    sparkApp
      .provide(Live)
      .fold(
        error => ErrorReturnCode,
        success => SuccessReturnCode
      )

}

object SparkApplication {

  val SuccessReturnCode = 0
  val ErrorReturnCode = 1

}

