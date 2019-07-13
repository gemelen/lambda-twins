package net.gemelen.spark.batch

import net.gemelen.spark.core._
import net.gemelen.spark.core.log.Logger.Live._
import org.apache.spark.sql.SparkSession
import zio.ZIO

object AppContext {
  
  class BatchApplication extends SparkApplication {

    def s: ZIO[Any, Nothing, SparkSession] = ZIO.succeed(
      SparkSession
        .builder()
        .master("local")
        .getOrCreate()
    )

    override def sparkApp: ZIO[SparkRuntime, Nothing, Int] = {
      for {
        _ <- logger.info("start")
        _ <- s.map(_.close())
        _ <- logger.info("end")
      } yield ( 0 )
    }

  }
}


object BatchApplication extends AppContext.BatchApplication
