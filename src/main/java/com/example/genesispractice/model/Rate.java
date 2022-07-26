package com.example.genesispractice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class Rate {
    private Status status;
    @JsonProperty("data")
    private List<Object> info;
}
