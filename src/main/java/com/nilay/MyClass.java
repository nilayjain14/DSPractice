package com.nilay;

import javax.swing.text.html.HTMLDocument;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Created by jainn on 8/27/17.
 */
public class MyClass {

    //public void method1(double d, int... values) {/* implementation elided */}
    //public void method2(int... values, double d) {/* implementation elided */}
//    private void doSomething(){
//        System.out.println("A");
//    }

    public static void main(String[] args){

        String topic = "parts.mds.parts_inventory_temp.clean";
//         final String filer = "/data/data-extractor/";
//        String path = filer + topic.replace(".","/");
//        System.out.println(path);

        long filename = System.currentTimeMillis();
        System.out.println(topic+"-"+filename);
//        TreeSet map = new TreeSet();
//
//
//        map.add("one");
//        map.add("two");
//        map.add("three");
//        map.add("four");
//        map.add("one");
//
//        Iterator it = map.iterator();
//        while(it.hasNext()){
//        System.out.println(it.next());}
        //String a = null;
        //System.out.println("a="+a);
        /*MyClass m = new MyClass1();

        try{
        m.doSomething();
        throw new Exception();
        } catch (Exception e) {
            System.out.println(",bc");
            System.exit(0);
        } finally {
            System.out.println("in finally");
        }

        /*int[] vals = {10, 20, 40};
        int i = vals[2] - vals[1] == 10 ? vals.length : vals.length + 1;
        System.out.println(i);*/
    }
}
