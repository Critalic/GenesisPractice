package com.example.genesispractice.controller;

import com.example.genesispractice.service.CoinMarketService;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMapAdapter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("api/rate")
@RestController
public class RestControllerRate {
    private final CoinMarketService service;
    private static final String apiKey = "03c90e1e-16ba-4d14-959a-52bbb624ab48";

    public RestControllerRate(CoinMarketService service) {
        this.service = service;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Double getAllRates() {
        Map<String, List<String>> params = new HashMap<>();
        params.put("start", Collections.singletonList("1"));
        params.put("limit", Collections.singletonList("1"));
        params.put("convert", Collections.singletonList("UAH"));
        params.put("aux", Collections.singletonList("date_added"));

        return service.getResp("/v1/cryptocurrency/listings/latest", apiKey, new MultiValueMapAdapter<>(params)).get();
    }
}
