package com.abo.shoppingclient.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductDTO {

    @NotBlank
    private String name;
    @NotNull
    private Float price;
    private String description;
    @NotBlank
    private String productIdentifier;
    @NotNull
    private CategoryDTO category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductIdentifier() {
        return productIdentifier;
    }

    public void setProductIdentifier(String productIdentifier) {
        this.productIdentifier = productIdentifier;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO categoryDTO) {
        this.category = categoryDTO;
    }

}
