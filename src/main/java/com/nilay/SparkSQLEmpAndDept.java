package com.nilay;

import org.apache.commons.io.FileUtils;
import org.apache.spark.SparkConf;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.io.File;
import java.io.IOException;

public class SparkSQLEmpAndDept {

    public static void main(String[] args) {

        SparkConf conf = new SparkConf().setMaster("local").setAppName("SparkSQLEmpAndDept");

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

        sparkSession.sql("LOAD DATA LOCAL INPATH 'src/main/resources/employes.csv' INTO TABLE emp");

        //1. Write a query and compute average salary (sal) of employees distributed by location (loc). Output shouldn't show any locations which don't have any employees.
        Dataset<Row> avg_sal = sparkSession.sql("select avg(sal), loc from(" +
                "select e.sal,d.loc, row_number() over (partition by d.loc order by d.loc) as location " +
                "from emp e inner join dept d on e.deptno = d.deptno)T group by loc");

        //2. Write a query and compute average salary (sal) of employees located in NEW YORK excluding PRESIDENT
        Dataset<Row> avg_sal_new_york = sparkSession.sql("select avg(sal), loc from (\n" +
                "select e.sal,d.loc, row_number() over (partition by d.loc order by d.loc) as location \n" +
                "from emp e inner join dept d on e.deptno = d.deptno\n" +
                "and e.job != 'PRESIDENT')T \n" +
                "where loc='NEW YORK' group by loc");

        //3. Write a query and compute average salary (sal) of four most recently hired employees
        Dataset<Row> avg_sal_recently_hired = sparkSession.sql("select avg(sal) from(\n" +
                "select e.sal,e.hiredate,d.loc, row_number() over (partition by d.loc order by e.hiredate desc) as location \n" +
                "from emp e inner join dept d on e.deptno = d.deptno)T\n" +
                "where location = 1");

        //4. Write a query and compute minimum salary paid for different kinds of jobs in DALLAS
        Dataset<Row> min_sal_different_job = sparkSession.sql("select sal,job,loc from\n" +
                "(select e.sal,e.job,d.loc,row_number() over (partition by e.job order by e.sal asc) as location\n" +
                "from emp e inner join dept d on e.deptno = d.deptno\n" +
                "and d.loc = 'DALLAS')T\n" +
                "where location = 1");

        avg_sal.show();
        avg_sal_new_york.show();
        avg_sal_recently_hired.show();
        min_sal_different_job.show();

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
