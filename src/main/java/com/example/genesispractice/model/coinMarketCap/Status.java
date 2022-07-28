package com.example.genesispractice.model.coinMarketCap;

import com.google.gson.annotations.SerializedName;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class Status {
    @NotNull
    @SerializedName("error_code")
    private Integer errorCode;

    @SerializedName("error_message")
    private String errorMessage;
}
