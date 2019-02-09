package com.example.rathana.retrofit_demo;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rathana.retrofit_demo.data.ServiceGenerator;
import com.example.rathana.retrofit_demo.data.service.ArticleService;
import com.example.rathana.retrofit_demo.model.Article;
import com.example.rathana.retrofit_demo.model.Author;
import com.example.rathana.retrofit_demo.model.Category;
import com.example.rathana.retrofit_demo.model.form.ArticleForm;
import com.example.rathana.retrofit_demo.model.response.ArticleResponse;
import com.google.gson.JsonObject;
import com.paginate.Paginate;
import com.paginate.recycler.LoadingListItemSpanLookup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity  implements ArticleAdapter.ArticleCallback {

    private static final int REQUEST_CODE = 2;
    RecyclerView rvArticle;
    ArticleAdapter articleAdapter;
    List<Article> articles=new ArrayList<>();
    ArticleService articleService;

    ProgressBar progressBar;
    TextView tvNoData;


    int currentPage=1;
    boolean isLoading=false;
    int totalPage=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        articleService=ServiceGenerator.createService(ArticleService.class);
        progressBar=findViewById(R.id.progressBar);
        tvNoData=findViewById(R.id.noData);

        setupUI();
        //getArticles();
    }

    private void setupUI() {
        rvArticle=findViewById(R.id.rvArticle);
        articleAdapter=new ArticleAdapter(articles,this);
        rvArticle.setLayoutManager(new LinearLayoutManager(this));
        rvArticle.setAdapter(articleAdapter);

        currentPage=1;
        isLoading=false;

        Paginate.with(rvArticle,callback)
                .setLoadingTriggerThreshold(2)
                .addLoadingListItem(true)
                .setLoadingListItemCreator(null)
                .setLoadingListItemSpanSizeLookup(()-> 2)
                .build();

    }

    private Paginate.Callbacks callback=new Paginate.Callbacks() {
        @Override
        public void onLoadMore() {
            Log.e("oooooo", "onLoadMore: "+currentPage );

            isLoading=true;
            new Handler().postDelayed(()->{
                getArticles(currentPage,5);
                currentPage++;
            },1000);

        }

        @Override
        public boolean isLoading() {
            return isLoading;
        }

        @Override
        public boolean hasLoadedAllItems() {
            return currentPage==totalPage;
        }
    };

    void getArticles(int page, int limit){
        progressBar.setVisibility(View.VISIBLE);
        articleService.getArticles(page,limit).enqueue(new Callback<ArticleResponse>() {
            @Override
            public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {
                ArticleResponse articleResponse=response.body();
                articleAdapter.updateArticles(articleResponse.getArticle());
                progressBar.setVisibility(View.GONE);
                if(articleResponse.getArticle().size()<=0)
                    tvNoData.setText("No Data");

                isLoading=false;
                totalPage=articleResponse.getPagination().getTotalPages();

            }

            @Override
            public void onFailure(Call<ArticleResponse> call, Throwable t) {
                Log.e("oooooo", "onFailure: "+t.toString());
                tvNoData.setText("error");
                progressBar.setVisibility(View.GONE);
                isLoading=false;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.option_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add:
                startActivityForResult(new Intent(this,AddArticleActivity.class),REQUEST_CODE);
                return  true;

            default: return  false;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode== REQUEST_CODE && resultCode==RESULT_OK){

            ArticleForm articleForm=data.getParcelableExtra("article");

            Article article=new Article();
            article.setTitle(articleForm.getTitle());
            article.setDescription(articleForm.getDescription());
            article.setImage(articleForm.getImage());
            Author author=new Author();
            author.setId(articleForm.getAuthor());
            article.setAuthor(author);
            Category cat=new Category();
            cat.setId(articleForm.getCategoryId());
            article.setCategory(cat);
            article.setCreatedDate(getCurrentDate());

            articleAdapter.setArticle(article);
            rvArticle.smoothScrollToPosition(0);
        }
    }

    private  String getCurrentDate(){
        return Calendar.getInstance().getTime().toString();
    }


    @Override
    public void onDelete(Article article, int pos) {
        articleService.deleteArticle(article.getId())
                .enqueue(new Callback<Response<JsonObject>>() {
                    @Override
                    public void onResponse(Call<Response<JsonObject>> call, Response<Response<JsonObject>> response) {
                        articleAdapter.deleteArticle(article,pos);
                        Toast.makeText(HomeActivity.this, "Delete success", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Response<JsonObject>> call, Throwable t) {

                    }
                });
    }

    @Override
    public void onArticleItemClicked(Article article) {
        Intent intent= new Intent(this, DetailActivity.class);
        Bundle b=new Bundle();
        b.putParcelable("article",article);
        b.putParcelable("author",article.getAuthor());
        intent.putExtras(b);
        startActivity(intent);
    }
}
