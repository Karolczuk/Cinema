package com.app.cinema.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Repertoire {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    private LocalTime time;

    private LocalDate date;

    @OneToMany(mappedBy = "repertoire")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonBackReference
    private List<Seat> seats;

    // dodane
//    @ManyToOne()
//    @JoinColumn(name = "seat_id")
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    private Seat seat;


}
