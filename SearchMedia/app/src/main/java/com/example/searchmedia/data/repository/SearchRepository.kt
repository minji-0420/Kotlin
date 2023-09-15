package com.example.searchmedia.data.repository

import com.example.searchmedia.data.model.Media
import retrofit2.Response
interface ImageSearchRepository {
    suspend fun searchImage(
        query: String,
        sort: String,
        page: Int,
        size: Int,
    ): Response<Media>
}