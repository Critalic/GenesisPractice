package com.example.genesispractice.service.rate;

import com.example.genesispractice.model.Rate;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMapAdapter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.reactive.function.client.WebClient;

@Service("CoinMarketCap")
public class CoinMarketService implements RateService {

    @Value("${coinMarketCap.api.key}")
    private String apiKey;
    private final WebClient client;


    public CoinMarketService(WebClient.Builder webClientBuilder) {
        this.client = webClientBuilder
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .baseUrl("https://pro-api.coinmarketcap.com")
            .build();
    }

    public Optional<String> requestHryvniaRate() {
        Map<String, List<String>> params = new HashMap<>();
        params.put("start", Collections.singletonList("1"));
        params.put("limit", Collections.singletonList("5"));
        params.put("convert", Collections.singletonList("UAH"));
        params.put("aux", Collections.singletonList("date_added"));

        String response = client.get()
            .uri(uriBuilder -> uriBuilder
                .path("/v1/cryptocurrency/listings/latest")
                .queryParams(new MultiValueMapAdapter<>(params))
                .build())
            .header("X-CMC_PRO_API_KEY", apiKey)
            .retrieve()
            .bodyToMono(String.class)
            .block();

        return parseSingleRateToUAH(new Gson().fromJson(response, Rate.class), response);
    }

    private Optional<String> parseSingleRateToUAH(@Valid Rate rate, String json) {


        if (rate.getInfo().size() != 1 || rate.getStatus().getError() != 0) { //TODO add validator
            return Optional.empty();
        }

        return Optional.of(JsonParser.parseString(json).getAsJsonObject()
            .get("data").getAsJsonArray().get(0).getAsJsonObject()
            .get("quote").getAsJsonObject()
            .get("UAH").getAsJsonObject()
            .get("price").getAsString());
    }
}
