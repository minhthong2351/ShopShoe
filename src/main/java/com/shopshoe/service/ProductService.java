package com.shopshoe.service;

import com.shopshoe.beans.Product;
import com.shopshoe.beans.WishList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ProductService  {
    List<Product> getAllProducts();

    Page<Product> findAll(Pageable pageable);
    Product getProductByName(String name);
    Product getProductById(Long id);
    public List<Product> listAll(String keyword);
    Product findById(Long id);
    Product findByMasp(String masp);
    List<Product> getAll();
    List<Product> getAllByBrand(String brand);
    List<Product> getAllByCategories(String categories);
}
