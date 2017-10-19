package com.components.database.models;


import com.components.jacksonfilters.View;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "word")
public class Word {

    @Id
    @Column(name = "id")
    @JsonView(View.WordView.class)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @JsonView(View.WordView.class)
    @Column(name = "russian")
    private String russian;

    @JsonView(View.WordView.class)
    @Column(name = "english")
    private String english;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "createDate")
    private Date createDate;

}