package com.ahmed.sltrafficcalculator.service;

import com.ahmed.sltrafficcalculator.api.Jour;
import com.ahmed.sltrafficcalculator.integration.rest.jour.dto.Result;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface JourResultMapper {

    List<Jour> entityToApi(List<Result> entity);

    default Jour entityToApi(Result entity) {
        Jour jour = Jour.builder()
                .lineNumber(entity.getLineNumber())
                .directionCode(entity.getDirectionCode())
                .journeyPatternPointNumber(entity.getJourneyPatternPointNumber())
                .build();

        return jour;
    }
}
