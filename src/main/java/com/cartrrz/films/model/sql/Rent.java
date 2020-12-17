package com.cartrrz.films.model.sql;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "rent")
public class Rent {

    @Column(name = "rent_date")
    private LocalDateTime rentDate;

    @Column(name = "return_date")
    private LocalDateTime returnDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
}
