package net.gemelen.spark.batch

import net.gemelen.spark.core.SparkApplication
import org.apache.spark.sql.SparkSession
import zio.{Task, ZIO}
import zio._
import zio.console._

object AppContext {
  
  class BatchApplication extends SparkApplication {

    def s: ZIO[Any, Nothing, SparkSession] = ZIO.succeed(
      SparkSession
        .builder()
        .master("local")
        .getOrCreate()
    )

    override def sparkApp: ZIO[Environment, Nothing, Int] = {
      for {
        _ <- putStrLn("start")
        _ <- s.map(_.close())
        _ <- putStrLn("end")
      } yield ( 0 )
    }

  }
}


object BatchApplication extends AppContext.BatchApplication
