package com.shopshoe.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String masp;
    private String name;
    private String description;
    private int price;
    private int sell_price;
    private int quantity;
    private String img;
    private int quantity_sold;
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;
    @ManyToOne
    @JoinColumn(name = "categories_id")
    private Categories categories;
    @ManyToOne
    @JoinColumn(name = "wishlist_id")
    private WishList wishlist;
    @OneToMany(mappedBy = "product")
    private List<OrderDetail> orderDetailList = new ArrayList<>();
}
