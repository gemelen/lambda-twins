package net.gemelen.spark.core.misc

import org.apache.spark.sql.SparkSession

object JarLoader {

  implicit class SparkSessionOps(val ss: SparkSession) extends AnyVal {
    /*
    * Passes list of jars paths to be loaded into SparkContext.
    */
    def loadJars(jars: List[String]): Unit = {
      jars
        .distinct
        .foreach(ss.sparkContext.addJar)
    }
  }

}

