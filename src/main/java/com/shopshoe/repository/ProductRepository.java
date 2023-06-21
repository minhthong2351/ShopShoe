package com.shopshoe.repository;

import com.shopshoe.beans.Brand;
import com.shopshoe.beans.Categories;
import com.shopshoe.beans.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE CONCAT(p.name,p.price) LIKE %?1%")
    public List<Product> search(String keyword);

    Product findByMasp(String masp);
    List<Product> findAllByBrand(Brand brand);
    List<Product> findAllByCategories(Categories categories);
}
