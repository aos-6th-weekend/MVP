package com.example.rathana.retrofit_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.rathana.retrofit_demo.data.ServiceGenerator;
import com.example.rathana.retrofit_demo.data.service.ArticleService;
import com.example.rathana.retrofit_demo.model.form.ArticleForm;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddArticleActivity extends AppCompatActivity {


    private EditText etTitle,etDesc, etAuthor,etCategory;
    private ImageView thumb;
    private Button btnSave;
    private ArticleService articleService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_article);

        etTitle=findViewById(R.id.artTitle);
        etDesc=findViewById(R.id.artDesc);
        etAuthor=findViewById(R.id.artAuthor);
        etCategory=findViewById(R.id.artCategory);
        btnSave=findViewById(R.id.btnSave);
        thumb=findViewById(R.id.imgThumb);

        articleService=ServiceGenerator.createService(ArticleService.class);


        btnSave.setOnClickListener(v->{
            ArticleForm form=new ArticleForm();

            form.setTitle(etTitle.getText().toString());
            form.setAuthor(Integer.parseInt(etAuthor.getText().toString()));
            form.setCategoryId(Integer.parseInt(etCategory.getText().toString()));
            form.setDescription(etDesc.getText().toString());
            form.setImage("https://www.boostability.com/wp-content/uploads/2014/09/Panda-Update.jpg");

            articleService.addArticle(form)
            .enqueue(new Callback<Response<JsonObject>>() {
                @Override
                public void onResponse(Call<Response<JsonObject>> call, Response<Response<JsonObject>> response) {
                    Toast.makeText(AddArticleActivity.this, "save success", Toast.LENGTH_SHORT).show();

                    Intent intent=new Intent();
                    Bundle b=new Bundle();
                    b.putParcelable("article",form);
                    intent.putExtras(b);
                    setResult(RESULT_OK,intent);
                    finish();
                }

                @Override
                public void onFailure(Call<Response<JsonObject>> call, Throwable t) {

                }
            });
        });

    }
}
