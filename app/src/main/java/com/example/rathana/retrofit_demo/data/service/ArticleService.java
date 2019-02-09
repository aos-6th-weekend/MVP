package com.example.rathana.retrofit_demo.data.service;

import com.example.rathana.retrofit_demo.model.form.ArticleForm;
import com.example.rathana.retrofit_demo.model.response.ArticleResponse;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ArticleService {

    @GET("/v1/api/articles")
    Call<ArticleResponse> getArticles(@Query("page") int page, @Query("limit") int limit);

    @POST("/v1/api/articles")
    Call<Response<JsonObject>>  addArticle(@Body ArticleForm articleForm);

    @DELETE("/v1/api/articles/{articleId}")
    Call<Response<JsonObject>> deleteArticle(@Path("articleId") int id);

}
