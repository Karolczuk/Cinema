package com.app.cinema.repository;

import com.app.cinema.model.Advert;
import com.app.cinema.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertRepository extends JpaRepository<Advert, Long> {
}
