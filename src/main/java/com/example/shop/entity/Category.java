package com.example.shop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.LinkedHashSet;

@Getter
@Setter
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String categoryName;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Collection<Game>game=new LinkedHashSet<>();





}
