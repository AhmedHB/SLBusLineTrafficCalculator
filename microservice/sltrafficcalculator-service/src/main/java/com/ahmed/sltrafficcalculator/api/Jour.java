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
public class Jour {
    @JsonProperty("LineNumber")
    public String lineNumber;
    @JsonProperty("DirectionCode")
    public String directionCode;
    @JsonProperty("JourneyPatternPointNumber")
    public String journeyPatternPointNumber;
    @JsonProperty("LastModifiedUtcDateTime")
    public String lastModifiedUtcDateTime;
    @JsonProperty("ExistsFromDate")
    public String existsFromDate;
}
