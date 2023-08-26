package com.example.applemarket

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemList(
    val image: Int,
    val title: String,
    val address: String,
    val price: String,
    val chatting: Int,
    val heart: Int,
    val reviews: Int,
    val likes: Int,
    val id: String,
    val detail: String
) : Parcelable

