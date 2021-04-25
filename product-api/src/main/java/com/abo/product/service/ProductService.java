package com.abo.product.service;

import com.abo.product.converter.DTOConverter;
import com.abo.product.exception.ProductNotFoundException;
import com.abo.product.model.Product;
import com.abo.product.repository.ProductRepository;
import com.abo.shoppingclient.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductDTO> getAll() {
        List<Product> products = productRepository.findAll();
        return products
                .stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    public List<ProductDTO> getProductByCategoryId(
            Long categoryId) {
        List<Product> products =
                productRepository.getProductByCategory(categoryId);
        return products
                .stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    public ProductDTO findByProductIdentifier(
            String productIdentifier) {
        Product product = productRepository.findByProductIdentifier(productIdentifier);
        if (product != null) {
            return DTOConverter.convert(product);
        }
        return null;
    }

    public ProductDTO save(ProductDTO productDTO) {
        Product product =
                productRepository.save(DTOConverter.convert(productDTO));
        return DTOConverter.convert(product);
    }

    public Product delete(long ProductId)
            throws ProductNotFoundException {
        Optional<Product> Product =
                productRepository.findById(ProductId);
        if (Product.isPresent()) {
            productRepository.delete(Product.get());
        }

        return null;
    }
}
