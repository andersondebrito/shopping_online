package com.abo.product.converter;

import com.abo.product.model.Category;
import com.abo.product.model.Product;
import com.abo.shoppingclient.dto.CategoryDTO;
import com.abo.shoppingclient.dto.ProductDTO;

public class DTOConverter {

    public static CategoryDTO convert(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        return categoryDTO;
    }
    public static ProductDTO convert(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setDescription(product.getDescription());
        productDTO.setProductIdentifier(
                product.getProductIdentifier());
        if (product.getCategory() != null) {
            productDTO.setCategory(
                    DTOConverter.convert(product.getCategory()));
        }
        return productDTO;
    }

    public static Product convert(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setProductIdentifier(
                productDTO.getProductIdentifier());
        if (productDTO.getCategory() != null) {
            product.setCategory(
                    DTOConverter.convert(productDTO.getCategory()));
        }
        return product;
    }

    public static Category convert(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        return category;
    }
}
