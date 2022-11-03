package com.ahmed.sltrafficcalculator.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stop {
    @JsonProperty("StopPointNumber")
    public String stopPointNumber;
    @JsonProperty("StopPointName")
    public String stopPointName;
}
