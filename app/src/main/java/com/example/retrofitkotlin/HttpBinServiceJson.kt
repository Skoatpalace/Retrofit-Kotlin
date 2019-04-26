package com.example.retrofitkotlin

import retrofit2.Call
import retrofit2.http.GET

interface HttpBinServiceJson {

    @GET("get")
    fun getUserInfo(): Call<GetData>
}