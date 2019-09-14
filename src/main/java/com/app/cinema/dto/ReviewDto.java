package com.app.cinema.dto;

import com.app.cinema.model.Admin;
import com.app.cinema.model.PremiumUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDto {

    private Long id;
    private PremiumUser premiumUser;
    private Admin admin;

}
