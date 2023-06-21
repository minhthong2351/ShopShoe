package com.shopshoe.repository;

import com.shopshoe.beans.Categories;
import com.shopshoe.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Long> {
    Categories findByName(String name);
}
