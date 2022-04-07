package com.geekbrains.spoonacular.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchGroceryProductResponse {

    private String type;
    private List<ProductView> products;
    private Long offset;
    private Long number;
    private Long totalProducts;
    private Long processingTimeMs;
    private Long expires;
    private Boolean isStale;

}
