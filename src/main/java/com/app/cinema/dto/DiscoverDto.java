package com.app.cinema.dto;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiscoverDto {

    private Long id;
    private Integer page;
    private Integer total_results;
    private Integer total_pages;
}
