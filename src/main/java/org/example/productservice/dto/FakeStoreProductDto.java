package org.example.productservice.dto;

import lombok.Getter;
import lombok.Setter;

import org.example.productservice.models.Category;
import org.example.productservice.models.Product;
@Getter
@Setter
public class FakeStoreProductDto {

    private Long id;
    private String title;
    private String image;
    private String description;
    private String category;
    private  Double price;

    public Product toProduct(){
        Product product = new Product();
        product.setId(id);
        product.setTitle(title);
        product.setImageUrl(image);
        product.setDescription(description);
        product.setPrice(price);

        Category productCategory = new Category();
        productCategory.setTitle(category);
        product.setCategory(productCategory);
        return product;
    }

}
