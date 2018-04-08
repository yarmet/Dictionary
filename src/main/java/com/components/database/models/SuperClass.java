package com.components.database.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@MappedSuperclass
public class SuperClass {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO )
    private long id;

}
