package com.ahmed.sltrafficcalculator.calculate;

import com.ahmed.sltrafficcalculator.api.BusLine;
import com.ahmed.sltrafficcalculator.api.BusLines;
import com.ahmed.sltrafficcalculator.api.Jour;
import com.ahmed.sltrafficcalculator.api.Stop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CalculateBusLineStops {
    private static final Logger LOG = LoggerFactory.getLogger(CalculateBusLineStops.class);
    public static BusLines calculateTopNrOfStops(List<Jour> allJoures, List<Stop> allStops, int max){
        List<Jour> distinctLineNumbers = allJoures.stream()
                .filter( distinctByKey(result -> result.lineNumber) )
                .collect( Collectors.toList() );

        Comparator<BusLine> compareByNrOfStops = Comparator
                .comparing(BusLine::getNrOfStops)
                .reversed();

        List<BusLine> busLineList = distinctLineNumbers.stream()
                .map(distinctLineNumber -> getBusLine(allJoures, distinctLineNumber))
                .map(busline -> mapToUniqueBusStops(allStops, busline))
                .sorted(compareByNrOfStops)
                .limit(max)

                .collect(Collectors.toList());

        BusLines busLines = BusLines.builder()
                .busLines(busLineList)
                .build();
        return busLines;
    }

    private static BusLine mapToUniqueBusStops(List<Stop> allStops, BusLine busline) {
        List<String> stopNames = new ArrayList<>();
        List<Jour> stopsForLineNumber = busline.getStopsForLineNumber();
        for(Jour jour : stopsForLineNumber){
            Stop stop1 = allStops.stream()
                    .filter(stop -> stop.getStopPointNumber().equalsIgnoreCase(jour.journeyPatternPointNumber))
                    .findFirst()
                    .orElse(null);
            if(stop1!=null) {
                stopNames.add(stop1.getStopPointName());
            }
        }
        List<String> listDistinct = stopNames.stream().distinct().collect(Collectors.toList());
        busline.setStopNames(listDistinct);
        busline.setNrOfStops(listDistinct.size());
        return busline;
    }


    private static BusLine getBusLine(List<Jour> allJoures, Jour distinctLineNumber) {
        List<Jour> stopsForLineNumber = allJoures.stream()
                .filter(jour -> jour.lineNumber.equalsIgnoreCase(distinctLineNumber.lineNumber)
                        && jour.getDirectionCode().equalsIgnoreCase("1")
                )
                .collect(Collectors.toList());

        //LOG.info("Test line: {} size: {}", distinctLineNumber.lineNumber, stopsForLineNumber.size());

        BusLine busLine = BusLine.builder()
                .lineNumber(distinctLineNumber.getLineNumber())
                .stopsForLineNumber(stopsForLineNumber)
                .nrOfStops(stopsForLineNumber.size())
                .build();
        return busLine;
    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor)
    {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
