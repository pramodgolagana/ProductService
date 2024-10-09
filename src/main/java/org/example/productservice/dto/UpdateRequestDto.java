package org.example.productservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.productservice.models.Category;
import org.example.productservice.models.Product;

@Setter
@Getter
public class UpdateRequestDto {
    private String title;
    private String image;
    private String description;
    private String category;
    private  Double price;

    public Product toProduct(){
        Product product = new Product();
        product.setTitle(title);
        product.setImageUrl(image);
        product.setDescription(description);
        product.setPrice(price);

        Category productCategory = new Category();
        productCategory.setTitle(category);
        product.setCategory(productCategory);
        return product;
    }

    public FakeStoreProductDto toFakestoreProductDto(){
        FakeStoreProductDto fakeStoreProductDto= new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setImage(image);
        fakeStoreProductDto.setDescription(description);
        fakeStoreProductDto.setPrice(price);
        fakeStoreProductDto.setTitle(category);
        return fakeStoreProductDto;
    }
}
