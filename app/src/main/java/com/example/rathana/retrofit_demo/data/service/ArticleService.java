package com.example.rathana.retrofit_demo.data.service;

import com.example.rathana.retrofit_demo.model.response.ArticleResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ArticleService {

    @GET("/v1/api/articles")
    Call<ArticleResponse> getArticles();

}
