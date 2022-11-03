package com.ahmed.sltrafficcalculator.service;

import com.ahmed.sltrafficcalculator.api.BusLines;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Api(value = "TrafficCalculatorService", description = "REST API for bus line information.")
public interface TrafficCalculatorService {
    @ApiOperation(
            tags = "get-buslines",
            value = "${api.traffic-calculator-service.get-buslines-with-max-nr-of-stops.description}",
            notes = "${api.traffic-calculator-service.get-buslines-with-max-nr-of-stops..notes}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad Request, invalid format of the request. See response message for more information."),
    })

    @GetMapping(
            value    = "/api/buslines/topnrofstops",
            produces = "application/json")
    @ResponseBody
    BusLines getTrafficCalculateTopNrOfStops( @Parameter(description = "Max result", example = "10", required = false) @RequestParam Optional<Integer> max);
}
