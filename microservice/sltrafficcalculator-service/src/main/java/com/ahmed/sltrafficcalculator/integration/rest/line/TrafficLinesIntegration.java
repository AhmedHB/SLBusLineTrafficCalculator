package com.ahmed.sltrafficcalculator.integration.rest.line;

import com.ahmed.sltrafficcalculator.integration.rest.line.dto.TrafficLines;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class TrafficLinesIntegration implements TrafficLinesService{
    private static final Logger LOG = LoggerFactory.getLogger(TrafficLinesIntegration.class);
    private final RestTemplate restTemplate;

    private final ObjectMapper mapper;

    private final String key;

    private final String trafficLinesServiceUrl;

    @Autowired
    public TrafficLinesIntegration(
            RestTemplate restTemplate,
            ObjectMapper mapper,
            @Value("${app.trafficLines-service.host}") String trafficLinesServiceHost,
            @Value("${app.key}") String key
    ){
        this.restTemplate = restTemplate;
        this.mapper = mapper;
        this.key = key;

        trafficLinesServiceUrl = "http://" + trafficLinesServiceHost  +
                "/api2/linedata.json?key="+ key + "&model=line&DefaultTransportModeCode=BUS";
    }

    public TrafficLines getTrafficLines(){
        try{
            String url = trafficLinesServiceUrl ;

            TrafficLines response = restTemplate.getForObject(url, TrafficLines.class);
            return response;

        }catch (HttpClientErrorException ex) {

            switch (ex.getStatusCode()) {

                default:
                    LOG.warn("Got a unexpected HTTP error: {}, will rethrow it", ex.getStatusCode());
                    LOG.warn("Error body: {}", ex.getResponseBodyAsString());
                    throw ex;
            }
        }
    }
}
