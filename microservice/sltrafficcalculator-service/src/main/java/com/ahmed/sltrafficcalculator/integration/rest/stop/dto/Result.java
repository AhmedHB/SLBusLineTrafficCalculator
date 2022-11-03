package com.ahmed.sltrafficcalculator.integration.rest.stop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Result {
    @JsonProperty("StopPointNumber")
    public String stopPointNumber;
    @JsonProperty("StopPointName")
    public String stopPointName;
    @JsonProperty("StopAreaNumber")
    public String stopAreaNumber;
    @JsonProperty("LocationNorthingCoordinate")
    public String locationNorthingCoordinate;
    @JsonProperty("LocationEastingCoordinate")
    public String locationEastingCoordinate;
    @JsonProperty("ZoneShortName")
    public String zoneShortName;
    @JsonProperty("StopAreaTypeCode")
    public String stopAreaTypeCode;
    @JsonProperty("LastModifiedUtcDateTime")
    public String lastModifiedUtcDateTime;
    @JsonProperty("ExistsFromDate")
    public String existsFromDate;
}
