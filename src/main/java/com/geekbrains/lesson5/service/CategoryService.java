package com.geekbrains.lesson5.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import com.geekbrains.lesson5.service.GetCategoryResponse;

public interface CategoryService {

    @GET ("categories/{id}")
    Call<GetCategoryResponse> getCategory(@Path("id") int id);

}
