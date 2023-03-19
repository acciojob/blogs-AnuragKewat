package com.driver.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String content;
    private Date pubDate;

    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL)
    List<Image> imageList = new ArrayList<>();

    @ManyToOne
    @JoinColumn
    User user;
}