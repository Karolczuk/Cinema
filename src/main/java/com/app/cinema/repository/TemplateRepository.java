package com.app.cinema.repository;

import com.app.cinema.model.Template;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemplateRepository extends JpaRepository<Template, Long> {

    Template findByName(String name);
}
