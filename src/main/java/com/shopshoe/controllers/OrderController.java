package com.shopshoe.controllers;

import com.shopshoe.beans.*;
import com.shopshoe.service.CartService;
import com.shopshoe.service.OrderService;
import com.shopshoe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @GetMapping("/order/{username}")
    public String showOrder(Model model, @PathVariable("username") String username) {
        User user = userService.findByUsername(username);
        model.addAttribute("items",cartService.getAllItems());
        model.addAttribute("total",cartService.getAmount());
        model.addAttribute("user",user);
        return "thanh_toan";
    }
    @PostMapping("/order/{username}")
    public String order(Model model, @PathVariable("username") String username, @RequestParam String address) {
        String orderAddress = address;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy ss:mm:HH");
        LocalDateTime now = LocalDateTime.now();
        User user = userService.findByUsername(username);
        Collection<CartItem> items = cartService.getAllItems();
        List<Product> productList = new ArrayList<>();
        int total = cartService.getAmount();
        //Order
        Order order = new Order();
        order.setOrderDate(formatter.format(now));
        order.setTotal(total);
        order.setUser(user);
        if(address.equals("")){
            orderAddress = user.getAddress();
        }
        order.setAddress(orderAddress);
        order.setStatus("Shipping");
        orderService.saveOrder(order);
        //OrderDetail
        for(CartItem item : items){
            OrderDetail orderDetail = new OrderDetail();
            Product product = new Product();
            product.setId(item.getId());
            product.setName(item.getPName());
            product.setPrice(item.getPrice());
            product.setQuantity(item.getQuantity());
            product.setMasp(item.getMasp());
            orderDetail.setProduct(product);
            orderDetail.setQuantity(item.getQuantity());
            orderDetail.setOrder(order);
            orderDetail.setName(item.getPName());
            orderDetail.setPrice(item.getPrice()*item.getQuantity());
            orderDetail.setMasp(item.getMasp());
            orderService.saveOrderDetail(orderDetail);
        }
        model.addAttribute("items",items);
        model.addAttribute("total",total);
        model.addAttribute("user",user);
        return "redirect:/your-orders/" + username;
    }
}
