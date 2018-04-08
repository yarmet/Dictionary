package com.components.database.models;


import com.components.jacksonfilters.View;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "word_groups")
public class WordGroup extends SuperClass implements Serializable {

    @Column(name = "name")
    @JsonView(View.WordGroupView.class)
    private String name;

    @Column(name = "creation_date")
    private Timestamp creationDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "wordGroup")
    private List<Word> words = new ArrayList<>();

}
