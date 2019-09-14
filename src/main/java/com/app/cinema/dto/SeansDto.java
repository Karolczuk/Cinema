package com.app.cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeansDto {

    private Long id;
    private Integer seatCount;
    private Integer rowNumber;
    private Integer roomNumber;
}
