package com.example.genesispractice.model;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class Status {
    @SerializedName("error_code")
    private Integer error;
}
