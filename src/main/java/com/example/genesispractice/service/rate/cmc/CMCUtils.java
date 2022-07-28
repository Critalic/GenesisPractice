package com.example.genesispractice.service.rate.cmc;

import com.example.genesispractice.exception.RateServiceValidationException;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.Optional;
import java.util.stream.IntStream;

public class CMCUtils {

    private CMCUtils() {
    }

    static String getRatePriceFromJson(String json, String fiatCurrency, String cryptoCurrency) {
        JsonArray currencyArray = JsonParser.parseString(json).getAsJsonObject()
            .get("data").getAsJsonArray();
        JsonObject currencyNode = IntStream.range(0, currencyArray.size())
            .filter(i -> currencyArray.get(i).getAsJsonObject().get("symbol").getAsString().equals(cryptoCurrency))
            .mapToObj(i -> currencyArray.get(i).getAsJsonObject())
            .findFirst().orElseThrow(() -> new RateServiceValidationException("Response doesn't contain the requested "
                + "currency"));

        return Optional.of(currencyNode
            .get("quote").getAsJsonObject()
            .get(fiatCurrency).getAsJsonObject()
            .get("price").getAsString()).orElseThrow(() -> new RateServiceValidationException(
            "Requested currency's price is missing"));
    }
}
