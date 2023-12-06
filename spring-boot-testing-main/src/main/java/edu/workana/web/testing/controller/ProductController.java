package edu.workana.web.testing.controller;

import edu.workana.web.testing.exception.InvalidRequestException;
import edu.workana.web.testing.model.Product;
import edu.workana.web.testing.model.ProductRequest;
import edu.workana.web.testing.service.design.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<Product> findAll() {
        log.info("ProductController :: findAll :: start");
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable("id") String id) {
        log.info("ProductController :: findById :: start");
        Product product = productService.findById(id);
        if (product == null) {
            throw new InvalidRequestException("Product not found with this id: " + id);
        }
        return product;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product save(@Valid @RequestBody ProductRequest productRequest) {
        log.info("ProductController :: save :: start");
        return productService.save(productRequest);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable("id") String id, @RequestBody ProductRequest productRequest) {
        log.info("ProductController :: update :: start");
        return new Product();
    }
}
