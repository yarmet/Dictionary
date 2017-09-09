package com.components.tables;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by yaya on 18.05.2015.
 */
@Data
@Entity
@Table(name = "role")
public class UserRole implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToMany(mappedBy = "userRoles")
    private Set<User> user = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private ListRole listRole;
}
