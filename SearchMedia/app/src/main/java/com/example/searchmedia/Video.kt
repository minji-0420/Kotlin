package com.example.searchmedia

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class Video(
    val imageMeta: VideoMeta,
    val imageDocuments: VideoDocuments
)
data class VideoDocuments(
    @SerializedName("items")
    val imageItem: MutableList<VideoItem>?,
)
data class VideoMeta(
    val totalCount: Int,
    val pageableCount: Int,
    val isEnd: Boolean
)
data class VideoItem(
    val title : String,
    val playTime : Int,
    val thumbnail : String,
    val url : String,
    val datetime : LocalDateTime,
    val author : String,
)
