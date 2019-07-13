package net.gemelen.spark.batch

import net.gemelen.spark.core.SparkApplication
import net.gemelen.spark.core.log.Logger
import net.gemelen.spark.core.log.Logger._
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

    override def sparkApp: ZIO[Logger, Nothing, Int] = {
      for {
        _ <- Live.logger.info("start")
        _ <- s.map(_.close())
        _ <- Live.logger.info("end")
      } yield ( 0 )
    }

  }
}


object BatchApplication extends AppContext.BatchApplication
