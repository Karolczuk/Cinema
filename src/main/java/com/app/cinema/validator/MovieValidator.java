package com.app.cinema.validator;


import com.app.cinema.dto.MovieDto;

import java.math.BigDecimal;
import java.util.Map;

public class MovieValidator extends AbstractValidator<MovieDto> {

    @Override
    public Map<String, String> validate(MovieDto movieDto) {

        errors.clear();

        if ( movieDto == null ) {
            errors.put("object", "object is null");
            return errors;
        }

        if ( !isNameValueCorrect(movieDto) ) {
            errors.put("title", "title is null");
        }

        if ( !isPriceCorrect(movieDto) ) {
            errors.put("price", "price are less or equal 0");
        }

        return errors;

    }

    private boolean isPriceCorrect( MovieDto movieDto ) {
        return movieDto.getPrice() != null && (movieDto.getPrice().compareTo(BigDecimal.ZERO)>0);
    }

    private boolean isNameValueCorrect( MovieDto movieDto ) {
        return movieDto.getTitle() != null;
    }

}
