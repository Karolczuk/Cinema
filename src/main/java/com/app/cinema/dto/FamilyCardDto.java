package com.app.cinema.dto;

import com.app.cinema.model.PremiumUser;
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
public class FamilyCardDto {

    private Long id;
    private BigDecimal discount;
    private LocalDateTime expirationDate;
    private BigDecimal price;
    private PremiumUser premiumUser;

}
