package org.example.productservice.services;

import org.example.productservice.dto.FakeStoreProductDto;
import org.example.productservice.dto.UpdateRequestDto;
import org.example.productservice.exception.ProductNotFoundException;
import org.example.productservice.models.Category;
import org.example.productservice.models.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@Service("FakeStoreProductService")
public class FakeStoreProductService implements ProductService{
    private RestTemplate restTemplate;

    FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }
    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundException {

       ResponseEntity< FakeStoreProductDto>  FakeStoreProductDtoresponse =restTemplate.getForEntity("https://fakestoreapi.com/products/"+productId,
                                                            FakeStoreProductDto.class);
       FakeStoreProductDto fakeStoreProductDto = FakeStoreProductDtoresponse.getBody();
       if(fakeStoreProductDto==null){
           throw new ProductNotFoundException("Product with ID : "+ productId +" doesn't exist. Retry anothe Product.");
       }
        return fakeStoreProductDto.toProduct();
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject("https://fakestoreapi.com/products",
                                                            FakeStoreProductDto[].class);
        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos){
            products.add(fakeStoreProductDto.toProduct());
        }

        return products;
    }

    @Override
    public Product deletProduct(Long productId) throws ProductNotFoundException {
        Product product = getSingleProduct(productId);
                restTemplate.delete("https://fakestoreapi.com/products/"+productId,
                FakeStoreProductDto.class );
        return product;
    }

    @Override
    public Product updateProduct(UpdateRequestDto requestDto, Long productId) {
        FakeStoreProductDto fakeStoreProductDto = requestDto.toFakestoreProductDto();
        restTemplate.put("https://fakestoreapi.com/products/"+productId,fakeStoreProductDto);
        Product product=requestDto.toProduct();
        product.setId(productId);
        return product;
    }

    @Override
    public List<Product> getProductsInCategory(String categoryName) {
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject("https://fakestoreapi.com/products/category/"+categoryName,
                                                                           FakeStoreProductDto[].class );

        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos){
            products.add(fakeStoreProductDto.toProduct());
        }

        return products;
    }

    @Override
    public List<Category> getALLCategorys() {
        String[] response = restTemplate.getForObject("https://fakestoreapi.com/products/categories",
                                                              String[].class );
        List<Category> categories = new ArrayList<>();
        for(String category : response){
            Category category1= new Category();
            category1.setTitle(category);
            categories.add(category1);
        }
        return  categories;
    }

    @Override
    public Product createProduct(String title, String description, String category, double price, String image) {
        FakeStoreProductDto request = new FakeStoreProductDto();
        request.setTitle(title);
        request.setDescription(description);
        request.setCategory(category);
        request.setPrice(price);
        request.setImage(image);

       FakeStoreProductDto response = restTemplate.postForObject("https://fakestoreapi.com/products",
                                                                    request,
                                                                    FakeStoreProductDto.class);
       return response.toProduct();
    }


}
