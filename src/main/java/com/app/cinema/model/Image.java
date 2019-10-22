package com.app.cinema.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue
    private Long id;

    private String poster;

    @ManyToOne()
    @JoinColumn(name = "movie_id")
    @JsonManagedReference // nie pozwala zapetlic sie zmiennym
    private Movie movie;

}
