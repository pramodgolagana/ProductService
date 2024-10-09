package org.example.productservice.repositories;

import org.example.productservice.models.Category;
import org.example.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    Category findByTitle(String category);
    boolean existsByTitle(String category);
}
