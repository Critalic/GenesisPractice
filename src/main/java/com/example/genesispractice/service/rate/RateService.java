package com.example.genesispractice.service.rate;

import java.util.Optional;

public interface RateService {

    Optional<String> requestCurrencyRate(String cryptoCurrency, String fiatCurrency, Integer responseSize);
}
