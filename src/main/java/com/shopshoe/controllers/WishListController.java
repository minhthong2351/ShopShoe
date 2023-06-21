package com.shopshoe.controllers;

import com.shopshoe.beans.Product;
import com.shopshoe.beans.User;
import com.shopshoe.beans.WishList;
import com.shopshoe.service.ProductService;
import com.shopshoe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WishListController {
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @GetMapping("/wishlist/{username}")
    public String showWishList(@PathVariable("username") String username, Model model) {
        List<WishList> wishList = userService.getWishListByUser(username);
        List<Product> productList = new ArrayList<>();
        for (WishList wish : wishList) {
            Product product = productService.findByMasp(wish.getMasp());
            productList.add(product);
        }
        if(productList.size() == 0) {
            return "wishlist";
        }
        model.addAttribute("products", productList);
        return "wishlist";
    }
    @GetMapping("/wishlist/{username}/{productId}")
    public String addToWishList(@PathVariable("username") String username, @PathVariable(name = "productId") long productId) {
        User user = userService.findByUsername(username);
        Product product = productService.findById(productId);
        userService.saveProductToWishList(user, product);
        return "redirect:/wishlist/" + username;
    }
    @GetMapping("/wishlist/{username}/{productId}/delete")
    public String deleteFromWishList(@PathVariable("username") String username, @PathVariable(name = "productId") long productId) {
        User user = userService.findByUsername(username);
        Product product = productService.findById(productId);
        userService.deleteProductFromWishList(user, product);
        return "redirect:/wishlist/" + username;
    }
}
