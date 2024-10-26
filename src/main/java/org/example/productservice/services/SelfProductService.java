package org.example.productservice.services;

import jakarta.persistence.Id;
import org.example.productservice.dto.UpdateRequestDto;
import org.example.productservice.exception.ProductNotFoundException;
import org.example.productservice.models.Category;
import org.example.productservice.models.Product;
import org.example.productservice.repositories.CategoryRepository;
import org.example.productservice.repositories.ProductRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service("SelfProductService")
public class SelfProductService implements ProductService{
    //@Autowired
    ProductRepository productRepository;
    CategoryRepository categoryRepository;
    private SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository=productRepository;
        this.categoryRepository=categoryRepository;
    }

    @Override
    public Product getSingleProduct(Long productId)  {
       Optional<Product> product = productRepository.findById(productId);
       Product product1;
       if(product.isPresent()){
           product1= product.get();
       }else{
           throw new NoSuchElementException("Product with Id: "+
                   productId+ " is not present. Try with another Id");
       }
       return  product1;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product deletProduct(Long productId)  {
        Product product = getSingleProduct(productId);
        productRepository.deleteById(productId);
        return product;
    }

    @Override
    public Product updateProduct(UpdateRequestDto requestDto, Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        Product product;
        if(optionalProduct.isPresent()){
            product=optionalProduct.get();
        }
        else{
            product= new Product();
            product.setId(id);
        }
        if(requestDto.getDescription()!=null){
            product.setDescription(requestDto.getDescription());
        }
        if(requestDto.getImage()!=null){
            product.setImageUrl(requestDto.getImage());
        }
        if(requestDto.getTitle()!=null){
            product.setTitle(requestDto.getTitle());
        }
        if(requestDto.getPrice()!=null){
            product.setPrice(requestDto.getPrice());
        }


        if(requestDto.getCategory()!=null){
            Category newcategory;

            if(categoryRepository.existsByTitle(requestDto.getCategory()) ){
                newcategory = categoryRepository.findByTitle(requestDto.getCategory());
            }
            else{

                Category categoryToSave= new Category();
                categoryToSave.setTitle(requestDto.getCategory());
                newcategory = categoryRepository.save(categoryToSave);
            }
            product.setCategory(newcategory);
        }

        return productRepository.save(product);
    }

    @Override
    public List<Product> getProductsInCategory(String category) {

        return productRepository.findByCategoryTitle(category);

    }

    @Override
    public List<Category> getALLCategorys() {
        return categoryRepository.findAll();
    }

    @Override
    public Page<Product> getPagebelProducts(int pageNumber, int pageSize, String sorting) {
        return switch (sorting) {
            case "id-asc" ->   productRepository.findAll(
                    PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, "id")
            );
            case "name-desc" -> productRepository.findAll(
                    PageRequest.of(pageNumber, pageSize, Sort.Direction.DESC, "title")
            );
            default -> productRepository.findAll(
                    PageRequest.of(pageNumber, pageSize)
            );
        };
    }

    @Override
    public Product createProduct(String title, String description, String category, double price, String image) {
        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageUrl(image);
        Category categoryFromDb=categoryRepository.findByTitle(category);
        if(categoryFromDb==null){
            Category newCategory = new Category();
            newCategory.setTitle(category);
            categoryFromDb= newCategory;
        }
        product.setCategory(categoryFromDb);

        return productRepository.save(product);
    }
}
