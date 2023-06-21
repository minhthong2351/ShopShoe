package com.shopshoe.service.impl;

import com.shopshoe.beans.Brand;
import com.shopshoe.beans.Categories;
import com.shopshoe.beans.Product;
import com.shopshoe.beans.User;
import com.shopshoe.repository.BrandRepository;
import com.shopshoe.repository.CategoriesRepository;
import com.shopshoe.repository.ProductRepository;
import com.shopshoe.repository.UserRepository;
import com.shopshoe.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private CategoriesRepository categoriesRepository;

    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products;
    }

    @Override
    public User findById(long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user"));
        return user;
    }

    @Override
    public int save(User user) {
        userRepository.save(user);
        return 0;
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<Brand> getAllBrands() {
        List<Brand> brands = brandRepository.findAll();
        return brands;
    }

    @Override
    public List<Categories> getAllCategories() {
        List<Categories> categories = categoriesRepository.findAll();
        return categories;
    }

    @Override
    public void saveBrand(Brand brand) {
        brandRepository.save(brand);
    }

    @Override
    public void saveCategories(Categories categories) {
        categoriesRepository.save(categories);
    }

    @Override
    public void deleteBrand(long id) {
        brandRepository.deleteById(id);
    }

    @Override
    public void deleteCategories(long id) {
        categoriesRepository.deleteById(id);
    }

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product findProductById(long id) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Invalid product"));
        return product;
    }

    @Override
    public void updateProduct(Product product) {
        productRepository.saveAndFlush(product);
    }

}
