package com.cartrrz.films.dto;

import lombok.Data;

@Data
public class JwtDTO {

    private String jwt;

    private Long userId;

    private String username;
}
