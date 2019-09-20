package com.app.cinema.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "family_cards")
public class FamilyCard {

    @Id
    @GeneratedValue
    private Long id;

    private BigDecimal discount;
    private LocalDateTime expirationDate;
    private BigDecimal price;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private User user;

//    @ManyToOne(cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "premiumUser_id")
//    private PremiumUser premiumUser;






}
