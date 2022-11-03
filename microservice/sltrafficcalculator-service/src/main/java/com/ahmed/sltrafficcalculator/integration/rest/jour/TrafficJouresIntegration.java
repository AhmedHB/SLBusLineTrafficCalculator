package com.ahmed.sltrafficcalculator.integration.rest.jour;

import com.ahmed.sltrafficcalculator.integration.rest.jour.dto.TrafficJoures;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class TrafficJouresIntegration implements TrafficJouresService {
    private static final Logger LOG = LoggerFactory.getLogger(TrafficJouresIntegration.class);
    private final RestTemplate restTemplate;

    private final ObjectMapper mapper;

    private final String key;

    private final String trafficJouresServiceUrl;

    @Autowired
    public TrafficJouresIntegration(
            RestTemplate restTemplate,
            ObjectMapper mapper,
            @Value("${app.trafficjoures-service.host}") String trafficJouresServiceHost,
            @Value("${app.key}") String key
    ){
        this.restTemplate = restTemplate;
        this.mapper = mapper;
        this.key = key;

        trafficJouresServiceUrl = "http://" + trafficJouresServiceHost  +
                "/api2/linedata.json?key="+ key + "&model=jour&DefaultTransportModeCode=BUS";
    }

    public TrafficJoures getTrafficJoures(){
        try{
            String url = trafficJouresServiceUrl;
            TrafficJoures response = restTemplate.getForObject(url, TrafficJoures.class);
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
