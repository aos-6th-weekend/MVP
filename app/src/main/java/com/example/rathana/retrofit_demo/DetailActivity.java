package com.example.rathana.retrofit_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.rathana.retrofit_demo.model.Article;
import com.example.rathana.retrofit_demo.model.Author;

public class DetailActivity extends AppCompatActivity {


    ImageView thumb;
    TextView artTitle,artAuthor,artDate,artDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        thumb=findViewById(R.id.thumb);
        artTitle=findViewById(R.id.artTitle);
        artDate=findViewById(R.id.artDate);
        artAuthor=findViewById(R.id.artAuthor);
        artDesc=findViewById(R.id.artDesc);

        Intent intent=getIntent();
        if(intent!=null){
            Article article= intent.getParcelableExtra("article");
            Author author=intent.getParcelableExtra("author");
            artTitle.setText(article.getTitle()!=null ? article.getTitle():"");
            artDate.setText(article.getCreatedDate()!=null ? article.getCreatedDate():"");
            artDesc.setText(article.getDescription()!=null ? article.getDescription():"");

            artAuthor.setText(author!= null ? author.getName() :"");

            if(article.getImage()!=null){
                Glide.with(this).load(article.getImage())
                        .into(thumb);
            }else{
                thumb.setImageResource(R.drawable.ic_delete_black_24dp);
            }

            Toast.makeText(this, author!= null ? author.getName() :"null", Toast.LENGTH_SHORT).show();
        }
    }
}
