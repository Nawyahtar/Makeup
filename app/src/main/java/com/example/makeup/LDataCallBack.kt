package com.example.makeup


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface LDataCallBack {
    @GET("products.json")
    fun getBrandName(
        @Query("brand")name :String
    ): Call<List<LData>>
}