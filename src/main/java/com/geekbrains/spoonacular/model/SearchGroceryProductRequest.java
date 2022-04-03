package com.geekbrains.spoonacular.model;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SearchGroceryProductRequest {

    private String query;
    private Long minCalories;
    private Long maxCalories;
    private Long minCarbs;
    private Long maxCarbs;
    private Long minProtein;
    private Long maxProtein;
    private Long minFat;
    private Long maxFat;
    private Long offset;
    private Long number;



}
