package com.geekbrains.clients;

import com.geekbrains.api.ResponseUtils;
import com.geekbrains.api.SpoonAcularApi;
import com.geekbrains.spoonacular.model.AutoCompleteProductResponse;
import com.geekbrains.spoonacular.model.SearchGroceryProductRequest;
import com.geekbrains.spoonacular.model.SearchGroceryProductResponse;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class SpoonAcularClient {

    // link must be finished with "/" //
    private static final String API_URL = "https://api.spoonacular.com/";
    private static final String API_KEY = "77f47619dd984e85b6d018d0189ef9fc";

    private final SpoonAcularApi api;

    public SpoonAcularClient() {

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(1000, TimeUnit.MILLISECONDS)
                .readTimeout(1000, TimeUnit.MILLISECONDS)
                .writeTimeout(1000, TimeUnit.MILLISECONDS)
                .callTimeout(Duration.ZERO)
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        this.api = retrofit.create(SpoonAcularApi.class);
    }

    public SearchGroceryProductResponse findAllProducts(
            SearchGroceryProductRequest request
    ) {
        Call<SearchGroceryProductResponse> responseCall = api.findAllProducts(
                API_KEY,
                request.getQuery(),
                request.getMinCalories(),
                request.getMaxCalories(),
                request.getMinCarbs(),
                request.getMaxCarbs(),
                request.getMinProtein(),
                request.getMaxProtein(),
                request.getMinFat(),
                request.getMaxFat(),
                request.getOffset(),
                request.getNumber()
        );
        return ResponseUtils.executeCall(responseCall);

    }

    public AutoCompleteProductResponse autocomplete(
            String query,
            Long number
    ) {
        Call<AutoCompleteProductResponse> responseCall = api.autocomplete(API_KEY, query, number);
        return ResponseUtils.executeCall(responseCall);
    }


}
