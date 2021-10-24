package com.example.myheadsup

import retrofit2.Call
import retrofit2.http.*

interface APIInterface {
    @Headers("Content-Type: application/json")
    @GET ("/celebrities/")
    fun showInfo(): Call <List<celebrityList?>>

    @Headers("Content-Type: application/json")
    @POST("/celebrities/")
    fun addInfo(@Body newUbring: addCelebrity): Call<addCelebrity>

    @Headers("Content-Type: application/json")
    @PUT("/celebrities/{pk}")
    fun updateInfo(@Path("pk") pk: Int, @Body updateUbring: celebrityList): Call<celebrityList>

    @Headers("Content-Type: application/json")
    @DELETE ("/celebrities/{pk}")
    fun deleteInfo(@Path("pk") pk: Int): Call<Void>
}