import sbt._

object v {
  val spark     = "2.4.3"
  val hadoop    = "2.8.5"
  val zio       = "1.0.0-RC10-1"
  val cats      = "2.0.0-M1"
  val guava     = "27.1-jre"
  val jackson   = "2.9.9"
  val kafka     = "2.3.0"
}

object deps {
  // Spark
  val yarn                    = "org.apache.spark"                  %% "spark-yarn"                    % v.spark force()
  val sql                     = "org.apache.spark"                  %% "spark-sql"                     % v.spark force()
  val sqlKafka                = "org.apache.spark"                  %% "spark-sql-kafka-0-10"          % v.spark force()
  val hive                    = "org.apache.spark"                  %% "spark-hive"                    % v.spark force()
  val streaming               = "org.apache.spark"                  %% "spark-streaming"               % v.spark force()
  val streamingKafka          = "org.apache.spark"                  %% "spark-streaming-kafka-0-10"    % v.spark force()
  //
  val hadoop                  = "org.apache.hadoop"                 %  "hadoop-client"                 % v.hadoop force()
  val hadoopAws               = "org.apache.hadoop"                 %  "hadoop-aws"                    % v.hadoop
  val kryoShaded              = "com.esotericsoftware"              %  "kryo-shaded"                   % "4.0.2"
  // ZIO
  val zio                     = "dev.zio"                           %% "zio"                           % v.zio
  // logging
  val scalaLogging            = "com.typesafe.scala-logging"        %% "scala-logging"                 % "3.9.2"
  val loggingBackend          = "org.slf4j"                         %  "slf4j-log4j12"                 % "1.7.16"
  // misc
  val typesafeConfig          = "com.typesafe"                      %  "config"                        % "1.3.4"
  val consulClient            = "com.orbitz.consul"                 %  "consul-client"                 % "1.3.6"
  val guava                   = "com.google.guava"                  %  "guava"                         % v.guava % Compile
  val avro                    = "org.apache.avro"                   %  "avro"                          % "1.9.0"
  val kafka                   = "org.apache.kafka"                  %% "kafka"                         % v.kafka
  // cats
  val catsCore                = "org.typelevel"                     %% "cats-core"                     % v.cats
  // jackson
  val jacksonAnnotations      = "com.fasterxml.jackson.core"        %  "jackson-annotations"           % v.jackson force()
  val jacksonCore             = "com.fasterxml.jackson.core"        %  "jackson-core"                  % v.jackson force()
  val jacksonDatabind         = "com.fasterxml.jackson.core"        %  "jackson-databind"              % v.jackson force()
  val jacksonDataformat       = "com.fasterxml.jackson.dataformat"  %  "jackson-dataformat-xml"        % v.jackson force()
  val jacksonScala            = "com.fasterxml.jackson.module"      %% "jackson-module-scala"          % v.jackson force()
  val jacksonDatatype         = "com.fasterxml.jackson.datatype"    %  "jackson-datatype-jsr310"       % v.jackson force()

  // combined
  val sparkEssentials = Seq(hadoop, hadoopAws, kryoShaded)
  val sparkModules = Seq(yarn, hive, sql, sqlKafka, streaming, streamingKafka)
  val spark = sparkEssentials ++ sparkModules
  val loggingFacade = scalaLogging
  val loggingFacility = Seq(scalaLogging, loggingBackend)
  val consul = Seq(consulClient, guava)
  val cats = Seq(catsCore)
  val jackson = Seq(jacksonAnnotations, jacksonCore, jacksonDatabind, jacksonDataformat, jacksonScala, jacksonDatatype)
}

