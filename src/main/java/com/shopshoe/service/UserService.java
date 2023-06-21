package com.shopshoe.service;


import com.shopshoe.beans.Product;
import com.shopshoe.beans.User;
import com.shopshoe.beans.WishList;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User findByUsername(String userName);
    int save(User user);
    User saveCheck(User user);
    int addUser(User user);
    boolean checkUsername(String userName);

    boolean checkEmail(String email);
    void updateUser(User user);
    void changePassword (long id, String password);
    List<WishList> getWishListByUser(String userName);
    void saveProductToWishList(User user, Product product);
    void deleteProductFromWishList(User user, Product product);
}
