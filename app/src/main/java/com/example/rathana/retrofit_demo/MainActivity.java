package com.example.rathana.retrofit_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.rathana.retrofit_demo.data.ServiceGenerator;
import com.example.rathana.retrofit_demo.data.service.CategoryService;
import com.example.rathana.retrofit_demo.model.response.CategoryResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    
    CategoryService categoryService;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
         categoryService=ServiceGenerator.createService(CategoryService.class);
         
         //Response<CategoryResponse> responseResponse= categoryService.getCategories().execute();
         
         categoryService.getCategories().enqueue(new Callback<CategoryResponse>() {
             @Override
             public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                 CategoryResponse categoryResponse=response.body();
                 Log.e("oooooo", "onResponse: "+categoryResponse.toString() );
             }

             @Override
             public void onFailure(Call<CategoryResponse> call, Throwable t) {
                 Log.e("ooooo", "onFailure: "+t.toString() );
             }
         });
        
    }
}
