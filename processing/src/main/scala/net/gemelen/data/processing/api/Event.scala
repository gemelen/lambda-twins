package net.gemelen.data.processing.api

trait Event {

  def date: String
  def body: Map[String, String]

  def apply(field: String): Option[String] = body.get(field)
}

