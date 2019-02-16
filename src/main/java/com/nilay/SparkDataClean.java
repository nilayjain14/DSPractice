package com.nilay;

import org.apache.commons.io.FileUtils;
import org.apache.spark.SparkConf;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.io.File;
import java.io.IOException;

public class SparkDataClean {

    public static void main(String[] args) {

        cleanUp();

        SparkConf conf = new SparkConf().setMaster("local").setAppName("SparkDataClean");
        SparkSession sparkSession = SparkSession.builder().config(conf).enableHiveSupport().getOrCreate();

//        sparkSession.sql("create table dummy(val string)");
//        sparkSession.sql("load data local inpath 'src/main/resources/emp.txt' into table dummy");
//
 //       sparkSession.sql("select regexp_replace(val, \"[\\\"|\\ |\\\t|\\.]+\", \"\") from dummy")
//                .write().text("/Users/jainn/temp");

        sparkSession.sql("CREATE TABLE IF NOT EXISTS emp (" +
                "  name string," +
                "  age string," +
                "  salary string," +
                "  benefits string," +
                "  department string)" +
                "row format delimited fields terminated by ',' lines terminated by '\n'");

        sparkSession.sql("LOAD DATA LOCAL INPATH 'src/main/resources/emp.txt' INTO TABLE emp");

        Dataset<Row> raw = sparkSession.sql("select \n" +
                "regexp_replace(name, \"[\\\"|\\ |\\\t]+\", \"\"),\n" +
                "regexp_replace(age, \"[\\\"|\\ |\\\t]+\", \"\"),\n" +
                "regexp_replace(salary, \"[\\\"|\\ |\\\t]+\", \"\"),\n" +
                "regexp_replace(benefits, \"[\\\"|\\ |\\\t]+\", \"\"),\n" +
                "regexp_replace(department, \"[\\\"|\\ |\\\t]+\", \"\")\n" +
                "from emp\n");



        raw.show();


        sparkSession.stop();
    }

    public static void cleanUp() {
        try {
            FileUtils.deleteDirectory(new File("metastore_db"));
            FileUtils.deleteDirectory(new File("spark-warehouse"));
            FileUtils.deleteQuietly(new File("derby.log"));
            //FileUtils.deleteDirectory(new File("/Users/nilay/temp"));
        } catch (IOException e) {
        }

    }
}
