package org.example.productservice.dto;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class CreateProductRequestDto {


    private String title;
    private String image;
    private String description;
    private String category;
    private  Double price;

    // Dto for each request so that in future
    // if the request needs additional parameters
    // you can easily add without impacting anything else.
}
