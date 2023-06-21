package com.shopshoe.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "wishlist")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;;
    private String masp;
    @OneToMany(mappedBy = "wishlist")
    private List<Product> productList = new ArrayList<>();
    @OneToMany(mappedBy = "wishlist")
    private List<User> userList = new ArrayList<>();

}
