package com.shopshoe.repository;

import com.shopshoe.beans.Brand;
import com.shopshoe.beans.Categories;
import com.shopshoe.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    Brand findByName(String name);
}
