package com.stage4;


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
import java.util.Locale;

public class App {

    private static final String URL = "http://tw-http-hunt-api-1062625224.us-east-2.elb.amazonaws.com/challenge/";
    private static final String INTPUT = "input";
    private static final String OUTPUT = "output";

    public static void main(String[] args) throws ParseException {

        callGetRequest();
    }

    private static void callGetRequest() throws ParseException {
        int count = 0;
        int totalPrice = 0;
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("userId", "SyFLgolhb");
        Model model = new Model();
        HttpEntity<Model> entity = new HttpEntity<Model>(model,headers);

        ResponseEntity<Model[]> intputResponse  = restTemplate.exchange(URL+INTPUT, HttpMethod.GET,entity, Model[].class);

        for(Model currentModel : intputResponse.getBody()){
            boolean validity;
            validity = isValidProduct(currentModel);
            if(validity){
                int tempPrice = currentModel.getPrice();
                totalPrice = totalPrice + tempPrice;
                count++;
            }
        }

        postValidCount(totalPrice,headers);
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

    private static void postValidCount(int totalPrice,HttpHeaders headers) {
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("totalValue", Integer.toString(totalPrice));

        HttpEntity<MultiValueMap<String, String>> outputResponse = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        ResponseEntity<String> responseFinal = restTemplate.postForEntity(URL+OUTPUT,outputResponse,String.class);

    }

}
