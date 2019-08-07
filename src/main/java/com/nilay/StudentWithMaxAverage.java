package com.nilay;

import java.util.*;
import java.util.stream.Collectors;

public class StudentWithMaxAverage {

    public static void main(String[] args) {

        String[][] score = {{"jerry","65"},{"bob","91"}, {"jerry","23"}, {"Eric","83"}};
        HashMap<String,List<String>> studentAndNumber = new HashMap<>();
        HashMap<String,String> studentAndAve = new HashMap<>();

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
            studentAndAve.put(map.getKey(),String.valueOf(average));
        }

        final HashMap<String, String> collect = studentAndAve.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, HashMap::new));

        for(Map.Entry<String,String> map : collect.entrySet()){
            System.out.println("map.getKey() = "+map.getKey());
            System.out.println("map.getValue() = "+map.getValue());
        }

    }
}
