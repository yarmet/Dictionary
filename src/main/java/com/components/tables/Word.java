package com.components.tables;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

/**
 * Created by ruslan on 01.12.16.
 */
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

    @Column(name = "date")
    private Date date;
}
