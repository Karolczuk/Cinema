package com.app.cinema.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String username;

    private String name;
    private String surname;
    private String email;
    private Integer age;
    private String password;

//    @Transient
//    private String passwordConfirmation;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<SalesStand> salesStands;

    @OneToMany(mappedBy = "user")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Reservation> bookings;

    // dodane1 18.09
    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Review> reviews;

    @OneToOne(mappedBy = "user")
    private LoyaltyCard loyaltyCard;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<FamilyCard> familyCards;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "seat_id")
    private Seat seat;

//   cfr
}
