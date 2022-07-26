package com.example.genesispractice.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Getter;

@Getter
public class Rate {
    private Status status;
    @SerializedName("data")
    private List<Object> info;
}
