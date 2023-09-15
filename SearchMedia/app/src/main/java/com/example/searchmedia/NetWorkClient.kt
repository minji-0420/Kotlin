package com.example.searchmedia

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetWorkClient {

    private const val URL = "https://dapi.kakao.com/v2/search/"

    private fun createOkHttpClient() : OkHttpClient {
        val interceptor = HttpLoggingInterceptor()

        if (BuildConfig.DEBUG)
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        else
            interceptor.level = HttpLoggingInterceptor.Level.NONE

        return OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20,TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .addNetworkInterceptor(interceptor)
            .build()
    }

    private val searchRetrofit = Retrofit.Builder()
        .baseUrl(URL)
        .client(createOkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val imageNetWork: NetWorkInterfaceImage = searchRetrofit.create(NetWorkInterfaceImage::class.java)
    val videoNetWork: NetWorkInterfaceVideo = searchRetrofit.create(NetWorkInterfaceVideo::class.java)

}