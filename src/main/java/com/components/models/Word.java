package com.components.models;


import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "word")
public class Word {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "russian")
    private String russian;

    @Column(name = "english")
    private String english;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "date")
    private Date date;
}