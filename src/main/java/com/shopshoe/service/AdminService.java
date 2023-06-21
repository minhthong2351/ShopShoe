package com.shopshoe.service;

import com.shopshoe.beans.Brand;
import com.shopshoe.beans.Categories;
import com.shopshoe.beans.Product;
import com.shopshoe.beans.User;

import java.util.List;
public interface AdminService  {
    List<User> getAllUsers();
    List<Product> getAllProducts();

    User findById(long id);

    int save(User user);

    void delete(long id);
    List<Brand> getAllBrands();
    List<Categories> getAllCategories();
    void saveBrand(Brand brand);
    void saveCategories(Categories categories);
    void deleteBrand(long id);
    void deleteCategories(long id);
    void saveProduct(Product product);
    void deleteProduct(long id);
    Product findProductById(long id);
    void updateProduct(Product product);
}
