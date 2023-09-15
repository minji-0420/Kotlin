package com.example.searchmedia

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface NetWorkInterfaceImage {
    @Headers("Authorization: KakaoAK ${BuildConfig.kakaoApiKey}")
    @GET("v2/search/image")
    suspend fun getImage(
        @Query("query") query: String,
        @Query("sort") sort: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Image
}
interface NetWorkInterfaceVideo {
    @Headers("Authorization: KakaoAK ${BuildConfig.kakaoApiKey}")
    @GET("v2/search/vclip")
    suspend fun getVideo(
        @Query("query") query: String,
        @Query("sort") sort: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ) : Video
}