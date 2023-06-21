package com.shopshoe.controllers;

import com.shopshoe.beans.*;
import com.shopshoe.service.AdminService;
import com.shopshoe.service.OrderService;
import com.shopshoe.service.ProductService;
import com.shopshoe.service.UserService;
import com.shopshoe.service.impl.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private UploadFileService upload;
    @ModelAttribute("user")
    public User userDto() {
        return new User();
    }
    @ModelAttribute("brand")
    public Brand brandDto() {
        return new Brand();
    }
    @ModelAttribute("categorie")
    public Categories categoriesDTO() {
        return new Categories();
    }
    @ModelAttribute("product")
    public Product productDTO() {
        return new Product();
    }
    @GetMapping("/admin-home")
    public String showAdmin(){
        return "admin/admin";
    }
    @GetMapping("/add-account")
    public String addAccount(){
        return "admin/add-account";
    }
    @PostMapping("/add-account")
    public String registerUserAccount(@ModelAttribute("user") User user) {
        int status = userService.addUser(user);
        if(status == 1) {
            return "redirect:/admin/add-account?emailerror";
        }
        if(status == 2) {
            return "redirect:/admin/add-account?usererror";
        }
        if(status == 3) {
            return "redirect:/admin/add-account?success";
        }
        return "redirect:/admin/add-account";
    }
    @GetMapping("/admin-accounts")
    public String showAdminaccounts(Model model){
        List<User> users = adminService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/accounts";
    }
    @GetMapping("edit-account/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        User user = adminService.findById(id);
        model.addAttribute("user", user);
        return "admin/account-details";
    }
    @PostMapping("/update-account/{id}")
    public String updateAccount(@PathVariable("id") long id,@ModelAttribute("customer") User user,BindingResult result) {
        if (result.hasErrors()) {
            user.setId(id);
            return "redirect:/admin/admin-accounts";
        }
        userService.updateUser(user);
        return "redirect:/admin/edit-account/" + id;
    }
    @GetMapping("delete-account/{id}")
    public String deleteStudent(@PathVariable("id") long id, Model model) {
        User user = adminService.findById(id);
        adminService.delete(user.getId());
        model.addAttribute("users", adminService.getAllUsers());
        return "admin/accounts";
    }
    @GetMapping("/admin-products")
    public String showAdminproducts(Model model){
        List<Product> users = adminService.getAllProducts();
        List<Brand> brands = adminService.getAllBrands();
        List<Categories> categories = adminService.getAllCategories();
        model.addAttribute("products", users);
        model.addAttribute("brands", brands);
        model.addAttribute("categories", categories);
        return "admin/products";
    }
    @GetMapping("/add-product")
    public String addProduct(Model model){
        model.addAttribute("brands", adminService.getAllBrands());
        model.addAttribute("categories", adminService.getAllCategories());
        return "admin/add-product";
    }
    @GetMapping("delete-product/{id}")
    public String deleteProduct(@PathVariable("id") long id, Model model) {
        adminService.deleteProduct(id);
        model.addAttribute("products", adminService.getAllProducts());
        model.addAttribute("brands", adminService.getAllBrands());
        model.addAttribute("categories", adminService.getAllCategories());
        return "admin/products";
    }
    @PostMapping("/add-product")
    public String addProduct(@ModelAttribute("product") Product product, @RequestParam("image") MultipartFile file) throws IOException {
        String image= upload.saveImage(file);
        product.setImg(image);
        adminService.saveProduct(product);
        return "redirect:/admin/admin-products";
    }
    @GetMapping("/edit-product/{id}")
    public String showProductForm(@PathVariable("id") long id, Model model) {
        Product product = adminService.findProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("brands", adminService.getAllBrands());
        model.addAttribute("categories", adminService.getAllCategories());
        return "admin/edit-product";
    }
    @PostMapping("/update-product/{id}")
    public String updateProduct(@PathVariable("id") long id,@ModelAttribute("product") Product product,BindingResult result) {
        if (result.hasErrors()) {
            product.setId(id);
            return "redirect:/admin/admin-products";
        }
        adminService.updateProduct(product);
        return "redirect:/admin/edit-product/" + id;
    }
    @PostMapping("/add-brand")
    public String addBrand(@ModelAttribute("brand") Brand brand) {
        adminService.saveBrand(brand);
        return "redirect:/admin/admin-products";
    }
    @PostMapping("/add-categories")
    public String addCategories(@ModelAttribute("categorie") Categories categories) {
        adminService.saveCategories(categories);
        return "redirect:/admin/admin-products";
    }
    @GetMapping("delete-brand/{id}")
    public String deleteBrand(@PathVariable("id") long id, Model model) {
        adminService.deleteBrand(id);
        model.addAttribute("products", adminService.getAllProducts());
        model.addAttribute("brands", adminService.getAllBrands());
        model.addAttribute("categories", adminService.getAllCategories());
        return "admin/products";
    }
    @GetMapping("delete-categories/{id}")
    public String deleteCategories(@PathVariable("id") long id, Model model) {
        adminService.deleteCategories(id);
        model.addAttribute("products", adminService.getAllProducts());
        model.addAttribute("categories", adminService.getAllCategories());
        model.addAttribute("brands", adminService.getAllBrands());
        return "admin/products";
    }
    @GetMapping("/admin-orders")
    public String showAdminorders(Model model){
        List<Order> orders = orderService.getAllOrder();
        model.addAttribute("orders", orders);
        return "admin/orders";
    }
    @GetMapping("order-details/{id}")
    public String showOrderDetails(@PathVariable("id") long id, Model model) {
        List<OrderDetail> orderDetails = orderService.getOrderDetailByOrderId(id);
        Order order = orderService.getOrderById(id);
        model.addAttribute("order", order);
        model.addAttribute("orderDetails", orderDetails);
        return "admin/order-details";
    }
    @GetMapping("done-order/{id}")
    public String doneOrder(@PathVariable("id") long id, Model model) {
        Order order = orderService.getOrderById(id);
        order.setStatus("Completed");
        orderService.updateOrder(order);
        return "redirect:/admin/admin-orders";
    }
    @GetMapping("cancel-order/{id}")
    public String cancelOrder(@PathVariable("id") long id, Model model) {
        Order order = orderService.getOrderById(id);
        order.setStatus("Canceled");
        orderService.updateOrder(order);
        return "redirect:/admin/admin-orders";
    }
    @GetMapping("delete-order/{id}")
    public String deleteOrder(@PathVariable("id") long id, Model model) {
        orderService.deleteOrder(id);
        model.addAttribute("orders", orderService.getAllOrder());
        return "redirect:/admin/admin-orders";
    }
}
