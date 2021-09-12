package com.example.makeup

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(
    @Json(name ="id") val id:Int,
    @Json(name = "brand") val brand:String,
    @Json(name ="name") val name:String,
    @Json(name= "price") val price:String,
    @Json(name = "image_link") val image:String
)