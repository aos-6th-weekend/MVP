package com.example.rathana.retrofit_demo.data.service;

import com.example.rathana.retrofit_demo.model.response.CategoryResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryService {

    @GET("/v1/api/categories")
    Call<CategoryResponse> getCategories();

}
