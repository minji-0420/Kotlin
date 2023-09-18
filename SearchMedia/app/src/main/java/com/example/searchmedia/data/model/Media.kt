package com.example.searchmedia.data.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime
data class Media(
    @SerializedName("meta")
    val mediaMeta: MediaMeta,
    @SerializedName("documents")
    val mediaDocuments: List<ImageItem>
)
data class MediaMeta(
    val totalCount: Int,
    val pageableCount: Int,
    val isEnd: Boolean
)
data class ImageItem(
    val collection : String,
    val thumbnail_url : String,
    val image_url : String,
    val width : Int,
    val height : Int,
    val display_sitename : String,
    val doc_url : String,
    val datetime: String,
    val isHeartFilled: Boolean = false
)