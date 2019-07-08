package com.example.shop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Orders {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "price")
    private BigDecimal price;
    //    @Column(name = "payment_method")
//    private EnumPayments paymentMethod;
    @Column(name = "realized")
    private boolean realized = false;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @CollectionTable(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "shipping_address_id")
    private Address shippingAddress;

    @OneToOne
    @JoinColumn(name = "billing_address_id")
    private Address billingAddress;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @Column(name = "game_id")
    private Collection<Game> books = new ArrayList<Game>();
}
