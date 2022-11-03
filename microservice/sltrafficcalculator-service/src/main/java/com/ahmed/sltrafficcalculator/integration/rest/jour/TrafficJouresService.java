package com.ahmed.sltrafficcalculator.integration.rest.jour;

import com.ahmed.sltrafficcalculator.integration.rest.jour.dto.TrafficJoures;
import com.ahmed.sltrafficcalculator.integration.rest.line.dto.TrafficLines;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public interface TrafficJouresService {

    @GetMapping(
            value    = "/trafficjoures",
            produces = "application/json")
    @ResponseBody
    TrafficJoures getTrafficJoures();

}
