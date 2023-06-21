package com.shopshoe.controllers;

import com.shopshoe.beans.Brand;
import com.shopshoe.beans.Categories;
import com.shopshoe.beans.Product;
import com.shopshoe.service.AdminService;
import com.shopshoe.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;


@Controller
public class ViewHomePageController {
    @Autowired
    private ProductService productService;
    @Autowired
    private AdminService adminService;

    @GetMapping("/")
    public String viewHomePage(Model model){
        model.addAttribute("products",productService.getAllProducts());
        return "index";
    }

//    @GetMapping ("/store")
//    public String viewStore(Model model){
//        model.addAttribute("products",productService.getAllProducts());
//        return "store";
//    }
    @GetMapping("/store/page")
    public String pagenate(Model model , @RequestParam("p") Optional<Integer> p){
        Pageable pageable= PageRequest.of(p.orElse(0),9);
        List<Brand> brands = adminService.getAllBrands();
        List<Categories> categories = adminService.getAllCategories();
        Page<Product> page= productService.findAll(pageable);
        model.addAttribute("pages",page);
        model.addAttribute("products",productService.getAllProducts());
        model.addAttribute("brands",brands);
        model.addAttribute("categories",categories);
        return "store";
    }
    @RequestMapping("/search")
    public String viewHomePage(Model model, @RequestParam("keyword") String keyword) {
        List<Product> listProducts = productService.listAll(keyword);
        List<Brand> brands = adminService.getAllBrands();
        List<Categories> categories = adminService.getAllCategories();
        model.addAttribute("products", listProducts);
        model.addAttribute("keyword", keyword);
        model.addAttribute("brands",brands);
        model.addAttribute("categories",categories);
        return "searchpage";
    }
}
