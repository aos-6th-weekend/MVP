package com.example.rathana.retrofit_demo.model.response;


import com.example.rathana.retrofit_demo.model.Article;
import com.example.rathana.retrofit_demo.model.Pagination;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArticleResponse {


    @SerializedName("DATA")
    private List<Article> article;
    @SerializedName("MESSAGE")
    private String message;
    @SerializedName("CODE")
    private String code;

    @SerializedName("PAGINATION")
    private Pagination pagination;


    public List<Article> getArticle() {
        return article;
    }

    public void setArticle(List<Article> article) {
        this.article = article;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    @Override
    public String toString() {
        return "ArticleResponse{" +
                "article=" + article +
                ", message='" + message + '\'' +
                ", code='" + code + '\'' +
                ", pagination=" + pagination +
                '}';
    }
}
