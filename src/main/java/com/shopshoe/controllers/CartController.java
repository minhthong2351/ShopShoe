package com.shopshoe.controllers;

import com.shopshoe.beans.CartItem;
import com.shopshoe.beans.Product;
import com.shopshoe.service.CartService;
import com.shopshoe.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("shopping-cart")
public class CartController {
    @Autowired
    ProductService productService;
    @Autowired
    CartService cartService;
    @GetMapping("/views")
    public String viewCart(Model model){
        model.addAttribute("items",cartService.getAllItems());
        model.addAttribute("total",cartService.getAmount());
        return "cart";
    }
    @GetMapping("add/{id}")
    public String addCart(@PathVariable("id") Long id){
        Product product=productService.getProductById(id);
        if(product!=null){
            CartItem item= new CartItem();
            item.setId(product.getId());
            item.setMasp(product.getMasp());
            item.setPName(product.getName());
            item.setPrice(product.getPrice());
            item.setImg(product.getImg());
            item.setDescription(product.getDescription());
//            item.setQuantity(product.getQuantity());
            cartService.addCart(item);
        }
        return "redirect:/shopping-cart/views";
    }
    @GetMapping("delete/{id}")
    public String removeCart(@PathVariable("id") Long id){
        cartService.remove(id);
        return "redirect:/shopping-cart/views";

    }
    @PostMapping("update")
    public  String update(@RequestParam("id") Long id, @RequestParam("quantity") Integer quantity){
        cartService.updateCart(id,quantity);
        return "redirect:/shopping-cart/views";
    }

}
