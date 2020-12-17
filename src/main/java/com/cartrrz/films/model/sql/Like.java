package com.cartrrz.films.model.sql;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "movie_like")
public class Like extends BaseEntity {

    @Column(name = "status")
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
}
