package com.example.searchmedia.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.searchmedia.data.model.ImageItem

class BookmarkViewModel : ViewModel() {

    private val _bookmarkedItems = MutableLiveData<MutableSet<ImageItem>>()
    val bookmarkedItems: LiveData<List<ImageItem>> get() = _bookmarkedItems.map { it.toList() }

    fun toggleBookmark(imageItem: ImageItem) {
        val currentSet = _bookmarkedItems.value ?: mutableSetOf()
        val imageItemToRemove = currentSet.find { it.image_url == imageItem.image_url }

        if (imageItemToRemove != null) {
            if (imageItemToRemove.isHeartFilled == imageItem.isHeartFilled) {
                currentSet.remove(imageItem)
            }
        } else {
            currentSet.add(imageItem)
        }

        _bookmarkedItems.value = currentSet
    }
}