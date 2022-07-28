package com.example.genesispractice.service.rate.cmc;

import com.example.genesispractice.model.coinMarketCap.Rate;
import com.example.genesispractice.service.rate.RateService;
import com.example.genesispractice.service.rate.ValidatorService;
import com.google.gson.Gson;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMapAdapter;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;

@Service("CoinMarketCap")
public class CoinMarketCapService implements RateService {

    @Value("${coinMarketCap.api.key}")
    private String apiKey;
    private final WebClient client;
    private final ValidatorService validator;

    public CoinMarketCapService(Builder webClientBuilder, ValidatorService validator) {
        this.validator = validator;
        this.client = webClientBuilder
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .baseUrl("https://pro-api.coinmarketcap.com")
            .build();
    }

    public Optional<String> requestCurrencyRate(String cryptoCurrency, String fiatCurrency, Integer responseSize) {
        Map<String, List<String>> params = new HashMap<>();
        params.put("start", Collections.singletonList("1"));
        params.put("limit", Collections.singletonList(responseSize.toString()));
        params.put("convert", Collections.singletonList(fiatCurrency));
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

        validator.validateRate(new Gson().fromJson(response, Rate.class));
        return CMCUtils.getRatePriceFromJson(response, fiatCurrency, cryptoCurrency);
    }
}
