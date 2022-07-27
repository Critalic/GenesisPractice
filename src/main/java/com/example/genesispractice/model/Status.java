package com.example.genesispractice.model;

import com.google.gson.annotations.SerializedName;
import javax.validation.constraints.Max;
import lombok.Getter;

@Getter
public class Status {
    @Max(0)
    @SerializedName("error_code")
    private Integer error;
}
