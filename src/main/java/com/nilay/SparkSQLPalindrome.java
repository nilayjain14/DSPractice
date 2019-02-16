package com.nilay;

import org.apache.commons.io.FileUtils;
import org.apache.spark.SparkConf;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import java.io.File;
import java.io.IOException;

public class SparkSQLPalindrome {

    public static void main(String[] args) {

        SparkConf conf = new SparkConf().setMaster("local")
                .set("spark.sql.shuffle.partitions", "3");

        SparkSession spark = SparkSession
                .builder()
                .appName("Java Spark SQL Palindrome")
                .config(conf)
                .enableHiveSupport()
                .getOrCreate();

        spark.sql("CREATE TABLE IF NOT EXISTS src (value STRING)");
        spark.sql("LOAD DATA LOCAL INPATH 'src/main/resources/temp.txt' INTO TABLE src");

        Dataset<Row> sqlDF_basic = spark.sql("SELECT value FROM src");
        Dataset<Row> sqlDF_reverse = spark.sql("SELECT reverse(value) FROM src");

        Dataset<Row> palindrome = sqlDF_basic.intersect(sqlDF_reverse);
        palindrome.show();

        spark.stop();
        cleanUp();
    }

    public static void cleanUp() {
        try {
            FileUtils.deleteDirectory(new File("metastore_db"));
            FileUtils.deleteDirectory(new File("spark-warehouse"));
            FileUtils.deleteQuietly(new File("derby.log"));
        } catch (IOException e) {
        }

    }
}
