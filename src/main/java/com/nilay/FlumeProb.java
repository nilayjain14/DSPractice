package com.nilay;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.Charset;

public class FlumeProb {

    private final static String userAgent = "nilay-user-agent";
    private final static String authToken = "lol";
    private static MediaType textPlain = new MediaType("text","plain", Charset.forName("UTF-8"));

    public static void main(String[] args){
        System.out.println(getLatestSchemaForTopic("hahha.lol.haha"));
    }

    public static String getLatestSchemaForTopic(String topic) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> httpEntity = genHttpEntity(textPlain);
        String url = generateUrl("/latest",topic);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET,
                httpEntity,String.class);
        return response.getBody();
    }

    private static <T> HttpEntity<T> genHttpEntity(MediaType mediaType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        headers.add(HttpHeaders.USER_AGENT, userAgent);
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer "+authToken);
        HttpEntity<T> httpEntity = new HttpEntity<T>(headers);
        return httpEntity;
    }

    private static String generateUrl(String uri, String topic){
        UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromHttpUrl("http://<domain-name>/<>url");
        urlBuilder.path("/");
        urlBuilder.path(uri);
        urlBuilder.queryParam("topic",topic);

        return urlBuilder.toUriString();
    }
}
