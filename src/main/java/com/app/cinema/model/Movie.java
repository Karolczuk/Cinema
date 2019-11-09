package com.app.cinema.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
//    @ElementCollection
//    private List<String> genre;
    private Integer duration;
    private LocalDate releaseDate;
    private BigDecimal price;

    @Lob
    private String description;
    private Boolean adult;

    @OneToMany(mappedBy = "movie")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonBackReference
    private List<Video> videos;

    @OneToMany(mappedBy = "movie")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<SalesStand> salesStands;

//    @OneToMany(mappedBy = "movie")
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    private Set<Reservation> reservations;

    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinTable
    private List<Genre> genres;

    @OneToMany(mappedBy = "movie")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonBackReference
    private List<Image> images;

    @OneToMany(mappedBy = "movie")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Repertoire> repertoires;

    @OneToMany(mappedBy = "movie")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Review> reviews;

}

