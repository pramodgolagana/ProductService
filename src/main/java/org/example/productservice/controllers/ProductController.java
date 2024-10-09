package org.example.productservice.controllers;

import org.example.productservice.dto.CreateProductRequestDto;
import org.example.productservice.dto.ErrorDto;
import org.example.productservice.dto.UpdateRequestDto;
import org.example.productservice.exception.ProductNotFoundException;
import org.example.productservice.exception.RunTimeException;
import org.example.productservice.models.Category;
import org.example.productservice.models.Product;
import org.example.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class ProductController {
    ProductService productService;

    ProductController(@Qualifier("SelfProductService") ProductService productService){
        this.productService= productService;
    }

    @PostMapping("/products")
    public Product  createProduct(@RequestBody CreateProductRequestDto payload){
        return productService.createProduct(payload.getTitle(),payload.getDescription(),
                payload.getCategory(),payload.getPrice(),payload.getImage());
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductDetails(@PathVariable("id") Long productId) throws ProductNotFoundException {
        Product product= productService.getSingleProduct(productId);
        ResponseEntity<Product> response = new ResponseEntity<>(product, HttpStatus.OK);
        return response;
    }

    @DeleteMapping("/products/{id}")
    public Product deletProduct(@PathVariable ("id") Long productId) throws ProductNotFoundException {
        return productService.deletProduct(productId);
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@RequestBody UpdateRequestDto payload, @PathVariable("id") Long id){
        return productService.updateProduct(payload,id);
    }
    @PatchMapping("/products/{id}")
    public Product partialUpdateProduct(@RequestBody UpdateRequestDto payload, @PathVariable("id") Long id){
        return productService.updateProduct(payload,id);
    }

    @GetMapping("/products")
    public List<Product> getAllProducts()  {
        //throw new RunTimeException("This is the run time exception ");
        return productService.getAllProducts();
    }

    @GetMapping("/products/category/{categoryName}")
    public List<Product> getProductsInCategory(@PathVariable("categoryName") String categoryName){
        return productService.getProductsInCategory(categoryName);
    }

    @GetMapping("/products/categories")
    public List<Category> getAllCategories(){
        return productService.getALLCategorys();
    }

}
