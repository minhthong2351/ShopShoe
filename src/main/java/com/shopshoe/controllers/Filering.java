package com.shopshoe.controllers;

import com.shopshoe.DTO.Convert.ProductConvertDTO;
import com.shopshoe.DTO.ProductDTO;
import com.shopshoe.beans.Product;
import com.shopshoe.beans.User;
import com.shopshoe.service.AdminService;
import com.shopshoe.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class Filering {
    @Autowired
    private ProductService productService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private ProductConvertDTO productConvertDTO;

    @RequestMapping("/filter")
    public List<ProductDTO> getAll(@RequestParam("categories") String categories, @RequestParam("brand") String brands) {
        List<Product> allProduct = new ArrayList<>();
        List<ProductDTO> productDTOS = new ArrayList<>();
        if (brands!=null){
            List<Product> products = productService.getAllByBrand(brands);
            for (Product product : products) {
                productDTOS.add(productConvertDTO.convert(product));
            }
        }
        if (categories!=null){
            List<Product> products1 = productService.getAllByCategories(categories);
            for (Product product1 : products1) {
                productDTOS.add(productConvertDTO.convert(product1));
            }
        }
        if(categories!=null && brands!=null){
            List<Product> productss = productService.getAllByCategories(categories);
            List<Product> productss1 = productService.getAllByBrand(brands);
            if(productss.size()> 0 && productss1.size() > 0){
                productDTOS.removeAll(productDTOS);
                for (Product product : productss) {
                    for (Product product1 : productss1) {
                        if(product.getId()==product1.getId()){
                            productDTOS.add(productConvertDTO.convert(product));
                        }
                    }
                }
            }
        }
        return productDTOS;
    }
}
