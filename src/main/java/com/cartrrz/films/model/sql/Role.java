package com.cartrrz.films.model.sql;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Data
@Table(name = "role")
public class Role extends BaseEntity{

    @Column(nullable = false, name = "role_key")
    private String roleKey;

    @Column(name = "role_description")
    private String roleDescription;

    @OneToMany(mappedBy = "role")
    private List<User> users;
}
