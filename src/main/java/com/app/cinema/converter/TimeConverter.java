package com.app.cinema.converter;

import com.app.cinema.dto.TimeDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;
import java.io.IOException;

@Slf4j
public class TimeConverter implements AttributeConverter<TimeDto, String> {


    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(TimeDto timeVisitDto) {
        String customerInfoJson = null;
        try {
            customerInfoJson = objectMapper.writeValueAsString(timeVisitDto);
        } catch (final JsonProcessingException e) {
            log.error("JSON writing error", e);
        }

        return customerInfoJson;
    }

    @Override
    public TimeDto convertToEntityAttribute(String s) {

        TimeDto timeDto = null;
        try {
            timeDto = objectMapper.readValue(s, TimeDto.class);
        } catch (final IOException e) {
            log.error("JSON reading error", e);
        } catch (NullPointerException e){
            log.warn("object in database doe not exist");
        }
        return timeDto;
    }
}
