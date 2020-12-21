package com.cartrrz.films.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RentDTO {

    private Long MovieId;

    private Long UserId;

    private LocalDateTime rentDate;

    private LocalDateTime returnDate;
}
