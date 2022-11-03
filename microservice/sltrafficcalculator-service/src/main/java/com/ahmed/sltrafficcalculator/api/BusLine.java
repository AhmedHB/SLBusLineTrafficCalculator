package com.ahmed.sltrafficcalculator.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BusLine {
    @ApiModelProperty(notes = "Bus line number")
    String lineNumber;
    @ApiModelProperty(notes = "Bus line stops")
    int nrOfStops;
    @JsonIgnore
    List<Jour> stopsForLineNumber;
    @ApiModelProperty(notes = "Bus line stop names")
    List<String> stopNames;
}
