package com.example.searchmedia.data.repository

import com.example.searchmedia.data.api.RetrofitInstance.searchApi
import com.example.searchmedia.data.model.Media
import retrofit2.Response

class ImageSearchRepositoryImpl : ImageSearchRepository {
    override suspend fun searchImage(
        query: String,
        sort: String,
        page: Int,
        size: Int
    ): Response<Media> {
        return searchApi.searchImage(query, sort, page, size)
    }
}