package com.example.shop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "cookies")
public class Cookies {

    @Id
    @GeneratedValue
    @Column(name = "cookie_id")
    private Long id;

    @Column(name= "cookie_name")
    private String cookieName;
    @Column(name = "value")
    private String value;
    @Column(name = "date")
    private Date date;

}
