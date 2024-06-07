package com.example.myapplication.network

import retrofit2.http.GET
import retrofit2.http.Path

interface WordApiService {
    @GET("/api/word")
    suspend fun getWord(): List<String>

    @GET("api/v2/entries/en/{word}")
    suspend fun checkWord(@Path("word") word: String): List<Any>
}
