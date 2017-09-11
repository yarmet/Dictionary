package com.components.models;


import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =GenerationType.AUTO )
    private long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

}
