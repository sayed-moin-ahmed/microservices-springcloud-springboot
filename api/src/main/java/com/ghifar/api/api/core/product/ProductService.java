package com.ghifar.api.api.core.product;

import com.ghifar.api.api.core.product.dto.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface ProductService {

    @GetMapping(value = "/product/{productId}")
    Product getProduct(@PathVariable int productId);
}
