package com.example.genesispractice.service;

import com.example.genesispractice.model.Rate;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
public class CoinMarketService {
    private final WebClient client;

    public CoinMarketService(WebClient.Builder webClientBuilder) {
        this.client = webClientBuilder
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .baseUrl("https://pro-api.coinmarketcap.com")
                .build();
    }

    public Optional<Double> getResp(String url, String apiKey, MultiValueMap<String, String> parameters) {
        String response = client.get()
                .uri(uriBuilder -> uriBuilder
                        .path(url)
                        .queryParams(parameters)
                        .build())
                .header("X-CMC_PRO_API_KEY", apiKey)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return parseMonoUAHRate(response);
    }

    private Optional<Double> parseMonoUAHRate(String json) {
        Rate rate = new Gson().fromJson(json, Rate.class);

        if(rate.getInfo().size()!=1 || rate.getStatus().getError()!=0) {
            return Optional.empty();
        }

        return Optional.of(JsonParser.parseString(json).getAsJsonObject()
                .get("data").getAsJsonArray().get(0).getAsJsonObject()
                .get("quote").getAsJsonObject()
                .get("UAH").getAsJsonObject()
                .get("price").getAsDouble());
    }
}
