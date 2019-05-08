package com.nilay;

import org.apache.commons.io.FileUtils;
import org.apache.spark.SparkConf;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import scala.collection.Seq;

import java.io.File;
import java.io.IOException;

public class SparkDFJoin {

    public static void main(String[] args) {

        SparkConf conf = new SparkConf().setMaster("local").setAppName("SparkDFJoin");

        SparkSession sparkSession = SparkSession
                .builder()
                .config(conf)
                .enableHiveSupport()
                .getOrCreate();

        sparkSession.sql("CREATE TABLE IF NOT EXISTS dept (" +
                "  deptno int," +
                "  dname string," +
                "  loc string)" +
                "row format delimited fields terminated by ',' lines terminated by '\n'");

        sparkSession.sql("LOAD DATA LOCAL INPATH 'src/main/resources/dept.csv' INTO TABLE dept");

        sparkSession.sql("CREATE TABLE IF NOT EXISTS emp (" +
                "  empno int," +
                "  ename string," +
                "  job string," +
                "  mgr string," +
                "  hiredate string," +
                "  sal int," +
                "  comm string," +
                "  deptno int)" +
                "row format delimited fields terminated by ',' lines terminated by '\n'");

        sparkSession.sql("LOAD DATA LOCAL INPATH 'src/main/resources/emp.csv' INTO TABLE emp");

        Dataset<Row> tableDF = sparkSession.sql("select deptno,dname,loc from dept");
        Dataset<Row> csvDF = sparkSession.sql("select empno,ename,deptno from emp");

        Dataset<Row> joined = csvDF.join(tableDF,csvDF.col("deptno").equalTo(tableDF.col("deptno")).and(csvDF.col("ename").equalTo(tableDF.col("dname"))), "left" );
        joined.select("empno","ename","loc").show();
        //joined.show();
        sparkSession.stop();
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
