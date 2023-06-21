package com.shopshoe.service.impl;

import com.shopshoe.beans.Product;
import com.shopshoe.beans.User;
import com.shopshoe.beans.WishList;
import com.shopshoe.repository.UserRepository;
import com.shopshoe.repository.WishListRepository;
import com.shopshoe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WishListRepository wishListRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User findByUsername(String userName) {
        User user = userRepository.findByUserName(userName);
        return user;
    }
    @Override
    public boolean checkUsername(String userName) {
        User user = userRepository.findByUserName(userName);
        if(user == null){
            return false;
        }
        return true;

    }
    @Override
    public boolean checkEmail(String email) {
        User user = userRepository.findByEmail(email);
        if(user == null){
            return false;
        }
        return true;
    }

    @Override
    public void updateUser(User user) {
        userRepository.saveAndFlush(user);
    }
    public void changePassword(long id, String password) {
        String passEncoder = passwordEncoder.encode(password);
        userRepository.changePassword(id, passEncoder);
    }

    @Override
    public List<WishList> getWishListByUser(String userName) {
        List<WishList> wishLists = wishListRepository.findAllByUserName(userName);
        return wishLists;
    }

    @Override
    public void saveProductToWishList(User user, Product product) {
        List<Product> products = new ArrayList<>();
        List<User> users = new ArrayList<>();
        products.add(product);
        users.add(user);
        WishList wishList = new WishList();
        wishList.setUserList(users);
        wishList.setProductList(products);
        wishList.setUserName(user.getUserName());
        wishList.setMasp(product.getMasp());
        wishListRepository.save(wishList);
    }

    @Override
    public void deleteProductFromWishList(User user, Product product) {
        wishListRepository.deleteByUserNameAndMasp(user.getUserName(), product.getMasp());
    }

    public int save(User user) {
        int status = 0;
        if(checkEmail(user.getEmail())) status = 1;
        if(checkUsername(user.getUserName()))status = 2;
        if(checkEmail(user.getEmail()) != true && checkUsername(user.getUserName()) != true){
            User users = new User(user.getUserName(),user.getFirstName(),
                    user.getLastName(), user.getEmail(),
                    passwordEncoder.encode(user.getPassword()), user.getPhone(), user.getAddress(), "USER");
            userRepository.save(users);
            status = 3;
        }
       return status;
    }

    @Override
    public User saveCheck(User user) {
        User users = new User(user.getUserName(),user.getFirstName(),
                user.getLastName(), user.getEmail(),
                passwordEncoder.encode(user.getPassword()), user.getPhone(), user.getAddress(), "USER");
        userRepository.save(users);
        return users;
    }


    public int addUser(User user) {
        int status = 0;
        if(checkEmail(user.getEmail())) status = 1;
        if(checkUsername(user.getUserName()))status = 2;
        if(checkEmail(user.getEmail()) != true && checkUsername(user.getUserName()) != true){
            User users = new User(user.getUserName(),user.getFirstName(),
                    user.getLastName(), user.getEmail(),
                    passwordEncoder.encode(user.getPassword()),user.getPhone(),user.getAddress(), user.getRole());
            userRepository.save(users);
            status = 3;
        }
        return status;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new ShopmeUserDetails(user);
    }
    
}
