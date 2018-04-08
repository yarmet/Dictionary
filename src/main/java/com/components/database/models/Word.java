package com.components.database.models;


import com.components.jacksonfilters.View;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "words")
public class Word extends SuperClass implements Serializable {


    @JsonView(View.WordView.class)
    @Column(name = "russian")
    private String russian;

    @JsonView(View.WordView.class)
    @Column(name = "english")
    private String english;

    @Column(name = "createDate")
    private Date createDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "word_groups_id")
    private WordGroup wordGroup;

}