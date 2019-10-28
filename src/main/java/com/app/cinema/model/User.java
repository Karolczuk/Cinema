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
    private String passwordConfirmation;


    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Role> roles;

    @OneToMany(mappedBy = "user")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<SalesStand> salesStands;

//    @OneToMany(mappedBy = "user")
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    private Set<Reservation> bookings;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Review> reviews;

    @OneToOne(mappedBy = "user")
    private Card loyaltyCard;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "user")
    private Set<Seat> seats;

}
