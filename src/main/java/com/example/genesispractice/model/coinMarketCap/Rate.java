package com.example.genesispractice.model.coinMarketCap;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class Rate {
    @NotNull(message = "API response status is null")
    private Status status;

    @NotNull(message = "API response data is null")
    @SerializedName("data")
    private List<Object> info;
}
