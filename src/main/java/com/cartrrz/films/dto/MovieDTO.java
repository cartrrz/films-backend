package com.cartrrz.films.dto;

import lombok.Data;

@Data
public class MovieDTO {

    private Long movieId;

    private String title;

    private String description;

    private Long stock;

    private Long rentPrice;

    private Long salePrice;

    private boolean availability;
}
