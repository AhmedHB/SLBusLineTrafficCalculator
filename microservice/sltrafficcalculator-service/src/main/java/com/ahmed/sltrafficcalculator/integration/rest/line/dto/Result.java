package com.ahmed.sltrafficcalculator.integration.rest.line.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Result {
    @JsonProperty("LineNumber")
    public String lineNumber;
    @JsonProperty("LineDesignation")
    public String lineDesignation;
    @JsonProperty("DefaultTransportMode")
    public String defaultTransportMode;
    @JsonProperty("DefaultTransportModeCode")
    public String defaultTransportModeCode;
    @JsonProperty("LastModifiedUtcDateTime")
    public String lastModifiedUtcDateTime;
    @JsonProperty("ExistsFromDate")
    public String existsFromDate;
}
