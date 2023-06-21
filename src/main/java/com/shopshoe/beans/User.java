package com.shopshoe.beans;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 6, max = 20)
    @Column(name = "username")
    private String userName;

    @Size(min = 2, max = 20)
    @Column(name = "first_name")
    private String firstName;

    @Size(min = 4, max = 20)
    private String lastName;

    private String email;

    private String password;
    @Size(min = 9, max = 11)

    private String phone;
    private String address;
    private String role;
    @ManyToOne
    @JoinColumn(name = "wishlist_id")
    private WishList wishlist;
    @OneToMany(mappedBy = "user")
    private List<Order> orderList;

    public User() {

    }

    public User(String userName, String firstName, String lastName, String email, String password, String phone, String address, String role) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.role = role;
    }

}
