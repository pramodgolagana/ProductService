package org.example.productservice.repositories;

import org.example.productservice.models.Product;
import org.example.productservice.repositories.projections.ProductProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository

public interface ProductRepository extends JpaRepository<Product,Long> {

@Query("select p from Product p where p.category.title= : title and p.id =: productid" )
Product getProductWithParticulerCategorynameAndId(@PathVariable("title") String categoryName,
                                                  @PathVariable("productid") Long productid);
 @Query("select p.title as title, p.id as id from Product p where p.category.id = :categoryId")
 List<ProductProjection> getTitlesAndIdOfProductsOfGivenCategory(@PathVariable("categoryId") Long categoryId);


Product save(Product product);
List<Product> findAll();
void deleteById(Long productId);

Product findByIdIs(Long productId);
List<Product> findByCategoryTitle(String category);

 @Query("SELECT p FROM Product p JOIN p.category c WHERE c.title = :categoryName")
 List<Product> getProductsByCategory(@Param("categoryName") String categoryName);

}
