package com.example.myheadsup

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APICelebrity {
    private var retrofit: Retrofit? = null
    fun getCelebrity(): Retrofit? {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val celebrity = OkHttpClient.Builder().addInterceptor(interceptor).build()
        retrofit = Retrofit.Builder()
            .baseUrl("https://dojo-recipes.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(celebrity)
            .build()
        return retrofit
    }
}