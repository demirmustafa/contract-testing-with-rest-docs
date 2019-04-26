package io.github.demirmustafa.producer.controller;

import io.github.demirmustafa.producer.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @author Mustafa Demir <mustafa.demir@sahabt.com>
 */
@RestController
@RequestMapping("/producer/api/v1/products")
public class ProductController {

    private static List<Product> products;

    static {
        products = products();
    }

    @GetMapping
    public List<Product> getProducts() {
        return products;
    }

    @GetMapping("/{id}")
    public Product product(@PathVariable Long id){
        if (1L == id){
            return products.get(0);
        }

        if(2L == id){
            return products.get(1);
        }

        throw new RuntimeException("Custom exception");
    }


    private static List<Product> products() {
        Product book = Product.builder()
                .id(1L)
                .name("Introduction to Java Programming")
                .description("Sample Description")
                .build();

        Product headPhone = Product.builder()
                .id(2L)
                .name("Creative WP-300")
                .description("Sample Description 2")
                .build();
        return Arrays.asList(book, headPhone);
    }

}
