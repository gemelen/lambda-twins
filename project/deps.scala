import sbt._

object v {
  val spark     = "2.4.3"
  val hadoop    = "2.8.5"
  val zio       = "1.0.0-RC8-12"
}

object deps {
  // Spark
  val yarn             = "org.apache.spark"                %% "spark-yarn"                    % v.spark force()
  val sql              = "org.apache.spark"                %% "spark-sql"                     % v.spark force()
  val sqlKafka         = "org.apache.spark"                %% "spark-sql-kafka-0-10"          % v.spark force()
  val hive             = "org.apache.spark"                %% "spark-hive"                    % v.spark force()
  val streaming        = "org.apache.spark"                %% "spark-streaming"               % v.spark force()
  val streamingKafka   = "org.apache.spark"                %% "spark-streaming-kafka-0-10"    % v.spark force()
  //
  val hadoop           = "org.apache.hadoop"               %  "hadoop-client"                 % v.hadoop force()
  val hadoopAws        = "org.apache.hadoop"               %  "hadoop-aws"                    % v.hadoop
  val kryoShaded       = "com.esotericsoftware"            %  "kryo-shaded"                   % "4.0.2"
  // ZIO
  val zio              = "dev.zio"                         %% "zio"                           % v.zio
  // logging
  val scalaLogging     = "com.typesafe.scala-logging"      %% "scala-logging"                 % "3.9.2"
  val loggingBackend   = "org.slf4j"                       %  "slf4j-log4j12"                 % "1.7.16"
  // misc
  val typesafeConfig   = "com.typesafe"                    % "config"                         % "1.3.4"

  // combined
  val sparkEssentials = Seq(hadoop, hadoopAws, kryoShaded)
  val sparkModules = Seq(yarn, hive, sql, sqlKafka, streaming, streamingKafka)

  val spark = sparkEssentials ++ sparkModules
  val logging = Seq(scalaLogging, loggingBackend)
}

