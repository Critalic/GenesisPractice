package com.example.genesispractice;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

@Disabled
public class ModelTest {
    String json = "{\n" +
            "    \"status\": {\n" +
            "        \"timestamp\": \"2022-07-25T18:41:22.600Z\",\n" +
            "        \"error_code\": 0,\n" +
            "        \"error_message\": null,\n" +
            "        \"elapsed\": 23,\n" +
            "        \"credit_count\": 1,\n" +
            "        \"notice\": null,\n" +
            "        \"total_count\": 9932\n" +
            "    },\n" +
            "    \"data\": [\n" +
            "        {\n" +
            "            \"id\": 1,\n" +
            "            \"name\": \"Bitcoin\",\n" +
            "            \"symbol\": \"BTC\",\n" +
            "            \"slug\": \"bitcoin\",\n" +
            "            \"date_added\": \"2013-04-28T00:00:00.000Z\",\n" +
            "            \"self_reported_circulating_supply\": null,\n" +
            "            \"self_reported_market_cap\": null,\n" +
            "            \"tvl_ratio\": null,\n" +
            "            \"last_updated\": \"2022-07-25T18:40:00.000Z\",\n" +
            "            \"quote\": {\n" +
            "                \"UAH\": {\n" +
            "                    \"price\": 800253.5452305846,\n" +
            "                    \"volume_24h\": 957713432599.5316,\n" +
            "                    \"volume_change_24h\": 16.497,\n" +
            "                    \"percent_change_1h\": -0.29749736,\n" +
            "                    \"percent_change_24h\": -3.64226054,\n" +
            "                    \"percent_change_7d\": -0.19617336,\n" +
            "                    \"percent_change_30d\": 3.83971944,\n" +
            "                    \"percent_change_60d\": -25.86807431,\n" +
            "                    \"percent_change_90d\": -42.99411844,\n" +
            "                    \"market_cap\": 15287368314092.914,\n" +
            "                    \"market_cap_dominance\": 41.5457,\n" +
            "                    \"fully_diluted_market_cap\": 16805324449842.139,\n" +
            "                    \"tvl\": null,\n" +
            "                    \"last_updated\": \"2022-07-25T18:40:12.000Z\"\n" +
            "                }\n" +
            "            }\n" +
            "        }\n" +
            "    ]\n" +
            "}";
    @Test
    void testModel() {
        Double price = JsonParser.parseString(json).getAsJsonObject()
                .get("data").getAsJsonArray().get(0).getAsJsonObject()
                .get("quote").getAsJsonObject()
                .get("UAH").getAsJsonObject()
                .get("price").getAsDouble();

        Rate rate = new Gson().fromJson(json, Rate.class);
        System.out.println(rate.status + " " + rate.data.size() + " " + price);
    }
}

class Rate {
    Status status;
    List<Object> data;
}
@Data
class Info {
    String name;
    Quote quote;
}
@Data
class Quote {
    Currency UAH;
}
@Data
class Currency {
    String price;
}
@Data
class Status {
    @SerializedName("error_code")
    Integer error;
}
