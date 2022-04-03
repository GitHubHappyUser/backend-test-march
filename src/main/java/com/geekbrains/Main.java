package com.geekbrains;

import com.geekbrains.clients.SpoonAcularClient;
import com.geekbrains.spoonacular.model.AutoCompleteProductResponse;
import com.geekbrains.spoonacular.model.SearchGroceryProductRequest;
import com.geekbrains.spoonacular.model.SearchGroceryProductResponse;

public class Main {

    public static void main(String[] args) {
        SpoonAcularClient client = new SpoonAcularClient();

        AutoCompleteProductResponse pasta = client.autocomplete("pasta", 3L);

        System.out.println(pasta);

        SearchGroceryProductResponse products = client.findAllProducts(
                SearchGroceryProductRequest.builder()
                        .query("pasta")
                        .minCalories(10L)
                        .maxCalories(1000L)
                        .number(3L)
                        .build()
        );

        products.getProducts().forEach(System.out::println);

    }
}
