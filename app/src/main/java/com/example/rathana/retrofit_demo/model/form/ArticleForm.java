package com.example.rathana.retrofit_demo.model.form;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ArticleForm  implements Parcelable {

    @SerializedName("IMAGE")
    private String image;
    @SerializedName("STATUS")
    private String status;
    @SerializedName("CATEGORY_ID")
    private int categoryId;
    @SerializedName("AUTHOR")
    private int author;
    @SerializedName("DESCRIPTION")
    private String description;
    @SerializedName("TITLE")
    private String title;

    public ArticleForm() {
    }

    protected ArticleForm(Parcel in) {
        image = in.readString();
        status = in.readString();
        categoryId = in.readInt();
        author = in.readInt();
        description = in.readString();
        title = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(image);
        dest.writeString(status);
        dest.writeInt(categoryId);
        dest.writeInt(author);
        dest.writeString(description);
        dest.writeString(title);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ArticleForm> CREATOR = new Creator<ArticleForm>() {
        @Override
        public ArticleForm createFromParcel(Parcel in) {
            return new ArticleForm(in);
        }

        @Override
        public ArticleForm[] newArray(int size) {
            return new ArticleForm[size];
        }
    };

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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
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

    @Override
    public String toString() {
        return "ArticleForm{" +
                "image='" + image + '\'' +
                ", status='" + status + '\'' +
                ", categoryId=" + categoryId +
                ", author=" + author +
                ", description='" + description + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
