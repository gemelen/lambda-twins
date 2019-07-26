package net.gemelen.spark.core.types

object Types {

  import org.apache.avro.generic.GenericRecord
  import net.gemelen.data.processing.api.errors.ProcessingError

  type Grain = Either[ProcessingError, GenericRecord]

  type BatchId = Long

}

package rdd {

  import net.gemelen.spark.core.types.Types._
  import org.apache.spark.rdd.RDD

  object Types {
    type Batch = RDD[Grain]
  }
}

package dataset {

  import net.gemelen.spark.core.types.Types._
  import org.apache.spark.sql.Dataset

  object Types {
    type Batch = Dataset[Grain]
  }
}

