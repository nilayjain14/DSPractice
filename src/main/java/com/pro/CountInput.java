package com.pro;


import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class CountInput {

    private static final String urlIntput = "http://tw-http-hunt-api-1062625224.us-east-2.elb.amazonaws.com/challenge/input";
    private static final String urlOutput = "http://tw-http-hunt-api-1062625224.us-east-2.elb.amazonaws.com/challenge/output";

    public static void main(String[] args){

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("userId", "SyFLgolhb");

        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<String> intputResponse = restTemplate.exchange(urlIntput, HttpMethod.GET, entity, String.class);
        String[] result =  intputResponse.getBody().split("\\{");

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("count", Integer.toString( result.length - 1));

        HttpEntity<MultiValueMap<String, String>> outputResponse = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        ResponseEntity<String> responseFinal = restTemplate.postForEntity(urlOutput,outputResponse,String.class);
    }

}
