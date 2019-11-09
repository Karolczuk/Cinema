package com.app.cinema.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Template {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Lob
    private String body;

}
