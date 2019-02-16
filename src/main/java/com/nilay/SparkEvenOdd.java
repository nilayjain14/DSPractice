package com.nilay;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.Arrays;
import java.util.List;

public class SparkEvenOdd {

    public static void main(String[] args){

        SparkConf conf = new SparkConf().setMaster("local").setAppName("Spark Even Odd Number");
        JavaSparkContext sparkContext = new JavaSparkContext(conf);

        List<Integer> data = Arrays.asList(1, 2, 3, 4, 5);
        JavaRDD<Integer> distData = sparkContext.parallelize(data);

        JavaRDD<Integer> evenNumbers = distData.filter(ele -> {
            if(ele % 2 == 0){
                return true;
            } else{
                return false;
            }
        });

        JavaRDD<Integer> oddNumbers = distData.subtract(evenNumbers);

//        JavaRDD<Integer> oddNumbers = distData.filter(ele -> {
//            if(ele % 2 != 0){
//                return true;
//            } else{
//                return false;
//            }
//        });

        List<Integer> even = evenNumbers.collect();
        List<Integer> odd = oddNumbers.collect();

        System.out.println(even);
        System.out.println(odd);

        sparkContext.stop();
    }
}
