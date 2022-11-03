package com.ahmed.sltrafficcalculator.service;

import com.ahmed.sltrafficcalculator.api.Stop;
import com.ahmed.sltrafficcalculator.integration.rest.stop.dto.Result;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StopResultMapper {
   List<Stop> entityToApi(List<Result> result);

   default Stop entityToApi(Result entity) {
      Stop stop = Stop.builder()
              .stopPointName(entity.stopPointName)
              .stopPointNumber(entity.getStopPointNumber())
              .build();

      return stop;
   }

}
