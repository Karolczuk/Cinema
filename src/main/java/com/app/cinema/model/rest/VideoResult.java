package com.app.cinema.model.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VideoResult {

  private String id;
  private String iso_639_1;
  private String iso_3166_1;
  private String key;
  private String name;
  private String site;
  private Integer size;
  private String type;

}
