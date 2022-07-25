package com.example.genesispractice.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CoinMarketService {
//    private final WebClient client;
//
//    public CoinMarketService(WebClient webClient) {
//        this.client = webClient;
//    }

    //    public String getResp(String url, String apiKey) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//        headers.add("X-CMC_PRO_API_KEY", apiKey);
//
//        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
//                .queryParam("start", "1")
//                .queryParam("limit", "5000")
//                .queryParam("convert", "USD");
//
//        restTemplate.exchange(builder.build().toUri(), HttpMethod.GET, )
//    }
    public String getResp(String url, String apiKey) {
        Map<String, String> params = new HashMap<>();
        params.put("start", "1");
        params.put("limit", "5000");
        params.put("convert", "USD");

        WebClient client = WebClient.builder()
                .baseUrl("url")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("X-CMC_PRO_API_KEY", apiKey)
                .defaultUriVariables(params)
                .build();
    }
}
