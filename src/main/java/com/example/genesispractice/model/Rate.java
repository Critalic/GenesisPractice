package com.example.genesispractice.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import lombok.Getter;

@Getter
public class Rate {
    private Status status;

    @Null
    @Size(max = 1)
    @SerializedName("data")
    private List<Object> info;
}
