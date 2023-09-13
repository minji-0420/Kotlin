package com.example.miseya

import com.example.miseya.Dust
import okhttp3.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface NetWorkInterface {
    @GET("getCtprvnRltmMesureDnsty")
    suspend fun getDust(@QueryMap param: HashMap<String, String>): Dust

}