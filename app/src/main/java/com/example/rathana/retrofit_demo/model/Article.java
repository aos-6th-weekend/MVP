package com.example.rathana.retrofit_demo.model;

import com.google.gson.annotations.SerializedName;

public class Article {

    @SerializedName("AUTHOR")
    private Author author;
    @SerializedName("CREATED_DATE")
    private String createdDate;
    @SerializedName("DESCRIPTION")
    private String description;
    @SerializedName("TITLE")
    private String title;
    @SerializedName("ID")
    private int id;
    @SerializedName("IMAGE")
    private  String image;
    @SerializedName("STATUS")
    private String status;

    @SerializedName("CATEGORY")
    private Category category;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Article{" +
                "author=" + author +
                ", createdDate='" + createdDate + '\'' +
                ", description='" + description + '\'' +
                ", title='" + title + '\'' +
                ", id=" + id +
                ", image='" + image + '\'' +
                ", status='" + status + '\'' +
                ", category=" + category +
                '}';
    }
}
