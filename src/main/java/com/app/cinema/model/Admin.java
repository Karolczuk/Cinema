package com.app.cinema.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "admins")
public class Admin extends User {

    @OneToMany(mappedBy = "admin")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Review> reviews;
}
