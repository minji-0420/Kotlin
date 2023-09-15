package com.example.searchmedia.data.api

import com.example.searchmedia.BuildConfig
import com.example.searchmedia.data.model.Media
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface SearchApi {
    @Headers("Authorization: KakaoAK ${BuildConfig.kakaoApiKey}")
    @GET("v2/search/image")
    suspend fun searchImage(
        @Query("query") query: String,
        @Query("sort") sort: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Response<Media>
}