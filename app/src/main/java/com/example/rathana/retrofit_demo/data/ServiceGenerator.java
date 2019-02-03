package com.example.rathana.retrofit_demo.data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static String BASE_URL="http://api-ams.me";
    private  static Retrofit.Builder builder=new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());



    public static <S> S createService(Class<S> service){

        //set up header

        return builder.build().create(service);
    }

}
