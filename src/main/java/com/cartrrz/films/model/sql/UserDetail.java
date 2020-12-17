package com.cartrrz.films.model.sql;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "user_detail")
public class UserDetail extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @OneToOne(mappedBy = "userDetail", cascade = CascadeType.ALL)
    private User user;
}
