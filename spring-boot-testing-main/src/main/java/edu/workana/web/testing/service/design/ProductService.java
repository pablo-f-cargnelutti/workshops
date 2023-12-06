package edu.workana.web.testing.service.design;

import edu.workana.web.testing.model.Product;
import edu.workana.web.testing.model.ProductRequest;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    Product findById(String productId);

    Product save(ProductRequest productRequest);
}
