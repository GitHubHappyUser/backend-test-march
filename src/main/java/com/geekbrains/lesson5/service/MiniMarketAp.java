package com.geekbrains.lesson5.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import com.geekbrains.lesson5.service.GetCategoryResponse;

public interface MiniMarketAp {

    @GET ("categories/{id}")
    Call<GetCategoryResponse> getCategory(@Path("id") int id);

}
