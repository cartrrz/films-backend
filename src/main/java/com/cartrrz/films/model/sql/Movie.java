package com.cartrrz.films.model.sql;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Data
@Table(name = "movie")
public class Movie extends BaseEntity{

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "stock")
    private Long stock;

    @Column(name = "rent_price")
    private Long rentPrice;

    @Column(name = "sale_price")
    private Long salePrice;

    @Column(name = "availability")
    private boolean availability;

    @OneToMany(mappedBy = "movie")
    private List<Rent> rents;

    @OneToMany(mappedBy = "movie")
    private List<Sale> sales;

    @OneToMany(mappedBy = "movie")
    private List<Like> likes;
}
