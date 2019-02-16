package com.nilay;

import java.util.Arrays;
import java.util.regex.Pattern;
import scala.Tuple2;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.StorageLevels;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;

// pls up this on the local "nc -lk 9999" (Netcat server)
// then start this streaming app
public class SparkStreamWordCount {

    private static final Pattern SPACE = Pattern.compile(" ");

    public static void main(String[] args) throws Exception {

    SparkConf sparkConf = new SparkConf().setMaster("local[2]").setAppName("SparkStreamWordCount");
    JavaStreamingContext ssc = new JavaStreamingContext(sparkConf, Durations.seconds(5));

    JavaReceiverInputDStream<String> lines = ssc.socketTextStream(
            "localhost", 9999, StorageLevels.MEMORY_AND_DISK_SER);
    JavaDStream<String> words = lines.flatMap(x -> Arrays.asList(SPACE.split(x)).iterator());

    JavaPairDStream<String, Integer> wordCounts = words.mapToPair(s -> new Tuple2<>(s, 1))
            .reduceByKey((i1, i2) -> i1 + i2);
            //.window(Durations.seconds(30),Durations.seconds(10));

    wordCounts.print(50);
    ssc.start();
   // ssc.remember(Durations.minutes(3));
    ssc.awaitTermination();
    }
}
