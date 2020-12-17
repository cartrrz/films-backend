package com.cartrrz.films.model.sql;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "user")
public class User extends BaseEntity{

    @Column(nullable = false, name = "username")
    private String username;

    @Column(nullable = false, name = "password")
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_detail_id")
    @JsonIgnore
    private UserDetail userDetail;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Rent> rents;

    @OneToMany(mappedBy = "user")
    private List<Sale> sales;

    @OneToMany(mappedBy = "user")
    private List<Like> likes;
}
