package com.example.searchmedia

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class Image(
    val imageMeta: ImageMeta,
    val imageDocuments: ImageDocuments
)
data class ImageDocuments(
    @SerializedName("items")
    val imageItem: MutableList<ImageItem>?,
)
data class ImageMeta(
    val totalCount: Int,
    val pageableCount: Int,
    val isEnd: Boolean
)
data class ImageItem(
    val collection : String,
    val thumbnailUrl : String,
    val imageUrl : String,
    val width : Int,
    val height : Int,
    val displaySiteName : Int,
    val docUrl : String,
    val dateTime : LocalDateTime
)
