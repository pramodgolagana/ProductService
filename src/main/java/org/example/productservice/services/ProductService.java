package org.example.productservice.services;

import org.example.productservice.dto.CreateProductRequestDto;
import org.example.productservice.dto.UpdateRequestDto;
import org.example.productservice.exception.ProductNotFoundException;
import org.example.productservice.models.Category;
import org.example.productservice.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(Long productId) throws ProductNotFoundException;
    List<Product> getAllProducts();
    Product deletProduct(Long productId) throws ProductNotFoundException;
    Product updateProduct(UpdateRequestDto requestDto,Long id);
    List<Product> getProductsInCategory(String category);
    List<Category> getALLCategorys();
    Page<Product> getPagebelProducts(int pageNumber, int pageSize,String sorting);

    Product createProduct(String title,
                          String description,
                          String category,
                          double price,
                          String image);
}
