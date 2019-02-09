package com.example.rathana.retrofit_demo.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Article implements Parcelable {

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



    public Article() {
    }

    protected Article(Parcel in) {
        createdDate = in.readString();
        description = in.readString();
        title = in.readString();
        id = in.readInt();
        image = in.readString();
        status = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(createdDate);
        dest.writeString(description);
        dest.writeString(title);
        dest.writeInt(id);
        dest.writeString(image);
        dest.writeString(status);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };

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
