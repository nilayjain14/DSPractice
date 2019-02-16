package com.nilay;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.StorageLevels;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.Minutes;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;

import java.util.Arrays;
import java.util.regex.Pattern;

public class SparkStreamUsingDataframeAndSQL {

    static class JavaRecord implements java.io.Serializable {
        private String word;

        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }
    }

    private static final Pattern SPACE = Pattern.compile(" ");

    public static void main(String[] args) throws Exception {

        SparkConf sparkConf = new SparkConf().setMaster("local[2]").setAppName("SparkStreamWordCount");
        JavaStreamingContext ssc = new JavaStreamingContext(sparkConf, Durations.seconds(5));

        JavaReceiverInputDStream<String> lines = ssc.socketTextStream(
                "localhost", 9999, StorageLevels.MEMORY_AND_DISK_SER);

        JavaDStream<String> words = lines.flatMap(x -> Arrays.asList(SPACE.split(x)).iterator());

        words.foreachRDD((rdd, time) -> {
            SparkSession spark = JavaSparkSessionSingleton.getInstance(rdd.context().getConf());

            // Convert JavaRDD[String] to JavaRDD[bean class] to DataFrame
            JavaRDD<JavaRecord> rowRDD = rdd.map(word -> {
                JavaRecord record = new JavaRecord();
                record.setWord(word);
                return record;
            });
            Dataset<Row> wordsDataFrame = spark.createDataFrame(rowRDD, JavaRecord.class);

            // Creates a temporary view using the DataFrame
            wordsDataFrame.createOrReplaceTempView("words");

            // Do word count on table using SQL and print it
            Dataset<Row> wordCountsDataFrame =
                    spark.sql("select word, count(*) as total from words group by word");
            System.out.println("========= " + time + "=========");
            wordCountsDataFrame.show();
        });
        ssc.start();
        ssc.awaitTermination();
    }

    static class JavaSparkSessionSingleton {
        private static transient SparkSession instance = null;
        public static SparkSession getInstance(SparkConf sparkConf) {
            if (instance == null) {
                instance = SparkSession
                        .builder()
                        .config(sparkConf)
                        .getOrCreate();
            }
            return instance;
        }
    }
}
