package com.app.cinema.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "seans")
public class Seans {

    @Id
    @GeneratedValue
    private Long id;

    private Integer seatCount;
    private Integer rowNumber;
    private Integer roomNumber;

    @OneToMany(mappedBy = "seans")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<User> users;

}
