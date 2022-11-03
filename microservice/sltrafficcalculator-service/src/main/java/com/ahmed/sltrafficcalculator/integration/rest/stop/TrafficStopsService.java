package com.ahmed.sltrafficcalculator.integration.rest.stop;

import com.ahmed.sltrafficcalculator.integration.rest.jour.dto.TrafficJoures;
import com.ahmed.sltrafficcalculator.integration.rest.stop.dto.TrafficStops;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public interface TrafficStopsService {

    @GetMapping(
            value    = "/trafficstops",
            produces = "application/json")
    @ResponseBody
    TrafficStops getTrafficStops();

}
