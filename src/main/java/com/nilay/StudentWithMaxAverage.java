package com.nilay;

import java.util.*;
import java.util.stream.Collectors;

public class StudentWithMaxAverage {

    public static void main(String[] args) {

        String[][] score = {{"bob","91"}, {"jerry","23"}, {"Eric","83"}, {"jerry","65"}};
        HashMap<String,List<String>> studentAndNumber = new HashMap<>();
        //if you just wanted to get name and average both then use HashMap approach
        HashMap<String,String> studentAndAve = new HashMap<>();
        //if you just wanted to get average not the name of the person then use ArrayList approach
        List<Double> averageList = new ArrayList<>();

        for(int r=0;r<score.length;r++){
            if(studentAndNumber.containsKey(score[r][0])){
                List<String> marks = studentAndNumber.get(score[r][0]);
                marks.add(score[r][1]);
                studentAndNumber.put(score[r][0],marks);
            }else{
                List<String> marks = new ArrayList<>();
                marks.add(score[r][1]);
                studentAndNumber.put(score[r][0],marks);
            }
        }


        for(Map.Entry<String,List<String>> map : studentAndNumber.entrySet()){
            List<String> marks = map.getValue();
            Double average = 0.0;
            Double sum = 0.0;
            for(String mark : marks){
                sum += Double.valueOf(mark);
            }
            average = sum/marks.size();
            //if you just wanted to get average not the name of the person then use a ArrayList approach
            averageList.add(average);
            //if you wanted to get name and average both then use HashMap approach
            studentAndAve.put(map.getKey(),String.valueOf(average));
        }

        //if you just wanted to get average not the name of the person then use a ArrayList approach
        Collections.sort(averageList);
        System.out.println(averageList.get(averageList.size()-1));

        //if you wanted to get name and average both then use HashMap approach
        final HashMap<String, String> collect = studentAndAve.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, HashMap::new));

        System.out.println( collect.entrySet().stream().findFirst().get().getValue());

    }
}
