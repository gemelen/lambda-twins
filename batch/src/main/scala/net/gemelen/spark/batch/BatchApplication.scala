package net.gemelen.spark.batch

import net.gemelen.spark.core._
import org.apache.spark.sql.SparkSession
import zio.ZIO

object AppContext {
  
  class BatchApplication extends SparkApplication {

    lazy val session: ZIO[Any, Nothing, SparkSession] = ZIO.succeed(
      SparkSession
        .builder()
        .master("local")
        .getOrCreate()
    )

    override def sparkApp: ZIO[SparkRuntime, Nothing, Int] = {
      import sparkRuntime.Environment.logger._

      for {
        _ <- info("start")
        count <- session.map { s =>
          s.range(1, 100).count()
        }
        _ <- session.map(_.close)
        _ <- info("end")
        _ <- info(s"count: $count")
      } yield ( 0 )
    }

  }
}


object BatchApplication extends AppContext.BatchApplication
