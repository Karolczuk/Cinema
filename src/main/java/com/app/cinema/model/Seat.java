package com.app.cinema.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "seats")
public class Seat {

    @Id
    @GeneratedValue
    private Long id;

    private Integer columnNumber;
    private Integer rowNumber;
//    private LocalTime time;
//    private LocalDate date;

//    @OneToOne(mappedBy = "seat")
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    private Reservation reservation;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;

//    @ManyToOne()
//    @JoinColumn(name = "movie_id")
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    private Movie movie;

    //dodane
//    @OneToMany(mappedBy = "seat")
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    @JsonBackReference
//    private List<Repertoire> repertoires;

    @ManyToOne()
    @JoinColumn(name = "repertoire_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Repertoire repertoire;


}
