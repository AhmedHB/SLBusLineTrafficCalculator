package com.ahmed.sltrafficcalculator.integration.rest.line;

import com.ahmed.sltrafficcalculator.integration.rest.line.dto.TrafficLines;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

public interface TrafficLinesService {

    @GetMapping(
            value    = "/trafficlines",
            produces = "application/json")
    @ResponseBody
    TrafficLines getTrafficLines();

}
