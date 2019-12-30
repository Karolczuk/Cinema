package com.app.cinema.repository;

import com.app.cinema.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    List<Genre> findByIdIn(List<Long> ids);

    List<Genre> findByNameIn(Set<String> name);
}
