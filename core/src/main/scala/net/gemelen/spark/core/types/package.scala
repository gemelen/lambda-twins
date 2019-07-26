package net.gemelen.spark.core.types

object Types {

  import org.apache.avro.generic.GenericRecord
  import net.gemelen.data.processing.api.ProcessingError

  type Data = Either[ProcessingError, GenericRecord]

}

package rdd {

  import net.gemelen.spark.core.types.Types._
  import org.apache.spark.rdd.RDD

  object Types {
    type DataRDD = RDD[Data]
  }
}

package dataset {

  import net.gemelen.spark.core.types.Types._
  import org.apache.spark.sql.Dataset

  object Types {
    type DataDS = Dataset[Data]
  }
}

