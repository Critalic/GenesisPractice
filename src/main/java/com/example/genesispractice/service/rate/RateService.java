package com.example.genesispractice.service.rate;

public interface RateService {

    String requestCurrencyRate(String cryptoCurrency, String fiatCurrency, Integer responseSize);
}
