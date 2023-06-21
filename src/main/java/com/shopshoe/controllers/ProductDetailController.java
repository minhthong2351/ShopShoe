package com.shopshoe.controllers;

import com.shopshoe.service.ProductService;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductDetailController {
    @Autowired
    private ProductService productService;

    @GetMapping("/detail")
    public ModelAndView productDetail(@RequestParam(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("details");
        mav.addObject("product", productService.getProductById(id));
        return mav;

    }
}
