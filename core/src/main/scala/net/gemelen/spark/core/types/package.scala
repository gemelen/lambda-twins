package net.gemelen.spark.core.types

import net.gemelen.data.processing.api.parsing.ParsingError
import net.gemelen.data.processing.api.validation.ValidationError
import net.gemelen.data.processing.api.enrichment.EnrichmentError

sealed abstract class ProcessingError

object ProcessingError {

  final case class Parsing(origin: ParsingError)       extends ProcessingError
  final case class Validation(origin: ValidationError) extends ProcessingError
  final case class Enrichment(origin: EnrichmentError) extends ProcessingError

  def apply(error: ParsingError)    = Parsing(error)
  def apply(error: ValidationError) = Validation(error)
  def apply(error: EnrichmentError) = Enrichment(error)
}

object Types {

  import org.apache.avro.generic.GenericRecord

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
