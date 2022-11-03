package com.ahmed.sltrafficcalculator.service;

import com.ahmed.sltrafficcalculator.api.BusLines;
import com.ahmed.sltrafficcalculator.api.Jour;
import com.ahmed.sltrafficcalculator.api.Stop;
import com.ahmed.sltrafficcalculator.calculate.CalculateBusLineStops;
import com.ahmed.sltrafficcalculator.integration.rest.jour.TrafficJouresIntegration;
import com.ahmed.sltrafficcalculator.integration.rest.jour.dto.ResponseData;
import com.ahmed.sltrafficcalculator.integration.rest.jour.dto.TrafficJoures;
import com.ahmed.sltrafficcalculator.integration.rest.line.TrafficLinesIntegration;
import com.ahmed.sltrafficcalculator.integration.rest.stop.TrafficStopsIntegration;
import com.ahmed.sltrafficcalculator.integration.rest.stop.dto.TrafficStops;
import com.ahmed.sltrafficcalculator.util.exceptions.InvalidInputException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class TrafficCalculatorServiceImpl implements TrafficCalculatorService{

    private static final Logger LOG = LoggerFactory.getLogger(TrafficCalculatorServiceImpl.class);
    @Value("${app.traffic-calculator-service.max}")
    private int maxValue;

    private final TrafficStopsIntegration trafficStopsIntegration;
    private final TrafficJouresIntegration trafficJouresIntegration;
    private final TrafficLinesIntegration trafficLinesIntegration;
    private final JourResultMapper jourResultMapper;
    private final StopResultMapper stopResultMapper;

    @Autowired
    public TrafficCalculatorServiceImpl(TrafficJouresIntegration trafficJouresIntegration,
                                        TrafficLinesIntegration trafficLinesIntegration,
                                        TrafficStopsIntegration trafficStopsIntegration,
                                        JourResultMapper jourResultMapper,
                                        StopResultMapper stopResultMapper) {
        this.trafficJouresIntegration = trafficJouresIntegration;
        this.trafficLinesIntegration = trafficLinesIntegration;
        this.trafficStopsIntegration = trafficStopsIntegration;
        this.jourResultMapper = jourResultMapper;
        this.stopResultMapper = stopResultMapper;
    }

    @Override
    public BusLines getTrafficCalculateTopNrOfStops(Optional<Integer> max) {
        validateRequest(max);

        TrafficJoures trafficJoures = trafficJouresIntegration.getTrafficJoures();
        ResponseData responseJouresData = trafficJoures.getResponseData();
        List<Jour> jourList  = jourResultMapper.entityToApi(responseJouresData.getResult());

        TrafficStops trafficStops = trafficStopsIntegration.getTrafficStops();
        com.ahmed.sltrafficcalculator.integration.rest.stop.dto.ResponseData responseStopsData = trafficStops.getResponseData();
        List<Stop> stopList  = stopResultMapper.entityToApi(responseStopsData.getResult());

        maxValue = max.isPresent() ?  max.get()  : maxValue;
        BusLines busLines = CalculateBusLineStops.calculateTopNrOfStops(jourList,stopList, maxValue);

        return busLines;
    }

    private void validateRequest(Optional<Integer> max){
        if(max.isPresent()){
            if(max.get()<0){
                throw new InvalidInputException( "Negative value " + max.get());
            }
        }
    }
}
