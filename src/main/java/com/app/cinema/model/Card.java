package com.app.cinema.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "loyalty_cards")

public class Card {
    @Id
    @GeneratedValue
    private Long id;

    private BigDecimal discount;
    private LocalDateTime expirationDate;
    private BigDecimal price;
    private Integer maxTicket;

    @Enumerated(EnumType.STRING)
    private CardType cardType;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", unique = true)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;


}
