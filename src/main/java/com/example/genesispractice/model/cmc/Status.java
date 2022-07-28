package com.example.genesispractice.model.cmc;

import com.google.gson.annotations.SerializedName;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class Status {
    @NotNull
    @SerializedName("error_code")
    private Integer errorCode;

    @SerializedName("error_message")
    private String errorMessage;
}
