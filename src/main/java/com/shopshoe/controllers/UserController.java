package com.shopshoe.controllers;

import com.shopshoe.beans.Order;
import com.shopshoe.beans.OrderDetail;
import com.shopshoe.beans.User;
import com.shopshoe.service.OrderService;
import com.shopshoe.service.ProductService;
import com.shopshoe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;

    @ModelAttribute("user")
    public User userRegistrationDto() {
        return new User();
    }

    @GetMapping("/registration")
    public String showRegistrationForm() {
        return "register";
    }
    @GetMapping("/profile/{username}")
    public String showProfileForm(@PathVariable("username") String username, Model model) {
        User user = userService.findByUsername(username);
        model.addAttribute("user", user);
        return "profile";
    }
    @PostMapping("/registration")
    public String registerUserAccount(@Valid User user, BindingResult result, @RequestParam(name = "rePassword") String rePassword ) {
        if (result.hasErrors()) {
            return "/register";
        }
        if(user.getPassword().equals(rePassword)){
            int status = userService.save(user);
            if(status == 1) {
                return "redirect:/registration?emailerror";
            }
            if(status == 2) {
                return "redirect:/registration?usererror";
            }
            if(status == 3) {
                return "redirect:/registration?success";
            }
            return "redirect:/registration";
        } else {
            return "redirect:/registration?rePassworderror";
        }

    }
    @PostMapping("/edit/{id}")
    public String editUserAccount(@PathVariable("id") long id, @ModelAttribute("user") User user, BindingResult result) {
        if (result.hasErrors()) {
            user.setId(id);
            return "redirect:/admin/admin-accounts";
        }
        userService.updateUser(user);
        return "redirect:/profile/" + user.getUserName();
    }
    @PostMapping("/changepassword/{id}")
    public String changePassword(@PathVariable("id") long id, @ModelAttribute("user") User user,@RequestParam(name = "newPassword") String newPassword, @RequestParam(name = "rePassword") String rePassword, BindingResult result){
        if(newPassword.equals(rePassword)){
            if (result.hasErrors()) {
                user.setId(id);
                return "redirect:/profile/" + user.getUserName();
            }
            userService.changePassword(id, newPassword);
            return "redirect:/profile/" + user.getUserName();
        } else {
            return "redirect:/profile/" + user.getUserName();
        }

    }
    @GetMapping("/your-orders/{username}")
    public String showOrder(@PathVariable("username") String username, Model model){
        User user = userService.findByUsername(username);
        List<Order> orders = orderService.getOrderByUserId(user.getId());
        List<OrderDetail> orderDetails = new ArrayList<>();
        for(Order order : orders){
            orderDetails.addAll(orderService.getOrderDetailByOrderId(order.getId()));
        }
        if(orders.isEmpty()){
            return "order";
        }
        model.addAttribute("user", user);
        model.addAttribute("orders", orders);
        model.addAttribute("orderDetails", orderDetails);
        return "order";
    }
}
