package net.gemelen.data.processing.api.enrichment

sealed trait EnrichmentError {

  def message(details: String): String

}

