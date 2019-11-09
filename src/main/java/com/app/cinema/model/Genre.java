package com.app.cinema.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "genres")
public class Genre {

    @Id
//    @GeneratedValue // Rest sam wygneruje Id
    private Long id;
    private String name;
//    @ManyToMany(mappedBy = "genres")
//    @EqualsAndHashCode.Exclude
//    private List<Movie> movies;
}
