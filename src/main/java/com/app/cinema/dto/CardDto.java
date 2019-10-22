package com.app.cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardDto {

    private Long id;
    private BigDecimal discount;
    private LocalDateTime expirationDate;
    private BigDecimal price;
    private Integer maxTicket;
}
