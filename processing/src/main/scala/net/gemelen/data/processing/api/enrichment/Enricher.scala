package net.gemelen.data.processing.api.enrichment

import net.gemelen.data.processing.api.Event

trait Enricher[E <: EnrichmentError] {

  def enrich(event: Event): Either[E, Event]

}

