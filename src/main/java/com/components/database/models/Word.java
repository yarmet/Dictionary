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
    @JsonView(View.Word.class)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @JsonView(View.Word.class)
    @Column(name = "russian")
    private String russian;

    @JsonView(View.Word.class)
    @Column(name = "english")
    private String english;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "date")
    private Date date;
}