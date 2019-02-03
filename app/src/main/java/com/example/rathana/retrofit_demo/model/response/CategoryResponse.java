package com.example.rathana.retrofit_demo.model.response;

import com.example.rathana.retrofit_demo.model.Category;
import com.example.rathana.retrofit_demo.model.Pagination;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryResponse {


    @SerializedName("PAGINATION")
    private Pagination pagination;
    @SerializedName("DATA")
    private List<Category> data;

    @SerializedName("MESSAGE")
    private String message;
    @SerializedName("CODE")
    private String code;

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public List<Category> getData() {
        return data;
    }

    public void setData(List<Category> data) {
        this.data = data;
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

    @Override
    public String toString() {
        return "CategoryResponse{" +
                "pagination=" + pagination +
                ", data=" + data +
                ", message='" + message + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
