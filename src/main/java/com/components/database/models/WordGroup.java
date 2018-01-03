package com.components.database.models;


import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "word_groups")
public class WordGroup {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "creation_date")
    private Timestamp creationDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "wordGroup")
    private List<Word> words = new ArrayList<>();

}