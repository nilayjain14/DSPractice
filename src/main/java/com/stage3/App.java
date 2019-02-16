package com.stage3;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class App {

    private static final String URL = "http://tw-http-hunt-api-1062625224.us-east-2.elb.amazonaws.com/challenge/";
    private static final String INTPUT = "input";
    private static final String OUTPUT = "output";

    public static void main(String[] args) throws ParseException {

        callGetRequest();
    }

    private static void callGetRequest() throws ParseException {
        int count = 0;

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("userId", "SyFLgolhb");
        Model model = new Model();
        HttpEntity<Model> entity = new HttpEntity<Model>(model,headers);

        ResponseEntity<Model[]> intputResponse  = restTemplate.exchange(URL+INTPUT, HttpMethod.GET,entity, Model[].class);

        Map<String,String> categoryCountMap = new HashMap<String,String>();

        for(Model currentModel : intputResponse.getBody()){
            boolean validity;
            validity = isValidProduct(currentModel);
            if(validity){
                if(categoryCountMap.containsKey(currentModel.getCategory())){
                    String value = categoryCountMap.get(currentModel.getCategory());
                    int valueInInt = Integer.parseInt(value);
                    categoryCountMap.put(currentModel.getCategory(),Integer.toString(valueInInt++));
                }else{
                    categoryCountMap.put(currentModel.getCategory(),Integer.toString(1));
                }
            }

        }

        postValidCategoryCount(categoryCountMap,headers);
  }

    private static boolean isValidProduct(Model currentModel) throws ParseException {

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date today = new Date();
        Date startDate = format.parse(currentModel.getStartDate());

        if (currentModel.getEndDate() != null) {
            Date endDate = format.parse(currentModel.getEndDate());
            return today.after(startDate) && today.before(endDate);
        }else{
            return today.after(startDate);
        }
    }

    private static void postValidCategoryCount(Map<String,String> categoryCountMap,HttpHeaders headers) {
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        for(Map.Entry<String, String> entry : categoryCountMap.entrySet()){
            map.add(entry.getKey(),entry.getValue());
        }

        HttpEntity<MultiValueMap<String, String>> outputResponse = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        ResponseEntity<String> responseFinal = restTemplate.postForEntity(URL+OUTPUT,outputResponse,String.class);

    }

}
