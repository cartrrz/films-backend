package com.cartrrz.films.model.sql;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "sale")
public class Sale extends BaseEntity{

    @Column(name = "sale_date")
    private LocalDateTime saleDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
}
