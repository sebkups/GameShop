package com.example.shop.repo;

import com.example.shop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);
    Category findId(Long categoryId);
    Category delete(Long id);
}
