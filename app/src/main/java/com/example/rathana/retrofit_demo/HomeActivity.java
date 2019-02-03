package com.example.rathana.retrofit_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.rathana.retrofit_demo.data.ServiceGenerator;
import com.example.rathana.retrofit_demo.data.service.ArticleService;
import com.example.rathana.retrofit_demo.model.Article;
import com.example.rathana.retrofit_demo.model.response.ArticleResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    RecyclerView rvArticle;
    ArticleAdapter articleAdapter;
    List<Article> articles=new ArrayList<>();
    ArticleService articleService;

    ProgressBar progressBar;
    TextView tvNoData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        articleService=ServiceGenerator.createService(ArticleService.class);
        progressBar=findViewById(R.id.progressBar);
        tvNoData=findViewById(R.id.noData);

        setupUI();
        getArticles();
    }

    private void setupUI() {
        rvArticle=findViewById(R.id.rvArticle);
        articleAdapter=new ArticleAdapter(articles,this);
        rvArticle.setLayoutManager(new LinearLayoutManager(this));
        rvArticle.setAdapter(articleAdapter);
    }

    void getArticles(){
        progressBar.setVisibility(View.VISIBLE);
        articleService.getArticles().enqueue(new Callback<ArticleResponse>() {
            @Override
            public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {
                ArticleResponse articleResponse=response.body();
                articleAdapter.updateArticles(articleResponse.getArticle());
                progressBar.setVisibility(View.GONE);
                if(articleResponse.getArticle().size()<=0)
                    tvNoData.setText("No Data");
            }

            @Override
            public void onFailure(Call<ArticleResponse> call, Throwable t) {
                Log.e("oooooo", "onFailure: "+t.toString());
                tvNoData.setText("error");
                progressBar.setVisibility(View.GONE);
            }
        });
    }

}
