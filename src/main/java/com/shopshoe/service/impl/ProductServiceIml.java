package com.shopshoe.service.impl;

import com.shopshoe.DTO.Convert.ProductConvertDTO;
import com.shopshoe.beans.Brand;
import com.shopshoe.beans.Categories;
import com.shopshoe.beans.Product;
import com.shopshoe.beans.WishList;
import com.shopshoe.repository.BrandRepository;
import com.shopshoe.repository.CategoriesRepository;
import com.shopshoe.repository.ProductRepository;
import com.shopshoe.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceIml implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private CategoriesRepository categoriesRepository;
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Product getProductByName(String name) {
        for (Product p : productRepository.findAll()) {
            if (p.getName().equals(name))
                return p;
        }
            return null;

    }
    @Override
    public Product getProductById(Long id) {
            for (Product p : productRepository.findAll()) {
                if (p.getId()==id)
                    return p;
            }
            return null;
        }
    public List<Product> listAll(String keyword) {
        if (keyword != null) {
            return productRepository.search(keyword);
        }
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Invalid product"));
        return product;
    }

    @Override
    public Product findByMasp(String masp) {
        Product product = productRepository.findByMasp(masp);
        return product;
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllByBrand(String brand) {
        Brand brand1 = brandRepository.findByName(brand);
        return productRepository.findAllByBrand(brand1);
    }

    @Override
    public List<Product> getAllByCategories(String categories) {
        Categories categories1 = categoriesRepository.findByName(categories);
        return productRepository.findAllByCategories(categories1);
    }

}
