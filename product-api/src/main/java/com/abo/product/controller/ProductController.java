package com.abo.product.controller;

import com.abo.product.exception.ProductNotFoundException;
import com.abo.product.model.Product;
import com.abo.product.service.ProductService;
import com.abo.shoppingclient.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product")
    public List<ProductDTO> getProducts() {
        List<ProductDTO> products = productService.getAll();
        return products;
    }

    @GetMapping("/product/category/{categoryId}")
    public List<ProductDTO> getProductByCategory(
            @PathVariable Long categoryId) {
        List<ProductDTO> products =
                productService.getProductByCategoryId(categoryId);
        return products;
    }

    @GetMapping("/product/{productIdentifier}")
    ProductDTO findById(@PathVariable String productIdentifier) {
        return productService
                .findByProductIdentifier(productIdentifier);
    }

    @PostMapping("/product")
    ProductDTO newProduct(
            @Valid @RequestBody ProductDTO productDTO) {
        return productService.save(productDTO);
    }

    @DeleteMapping("/product/{id}")
    Product delete(@PathVariable Long id)
            throws ProductNotFoundException {
        return productService.delete(id);
    }
}
