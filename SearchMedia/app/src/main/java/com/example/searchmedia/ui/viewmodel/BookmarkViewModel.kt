package com.example.searchmedia.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.searchmedia.data.model.ImageItem

class BookmarkViewModel : ViewModel() {

    private val _bookmarkedItems = MutableLiveData<MutableSet<ImageItem>>()
    val bookmarkedItems: LiveData<List<ImageItem>> get() = _bookmarkedItems.map {it.toList()}
    fun toggleBookmark(imageItem: ImageItem) {
        val currentSet = _bookmarkedItems.value ?: mutableSetOf()
        Log.d("toggle", imageItem.toString())
        if (currentSet.contains(imageItem)) {
            currentSet.remove(imageItem)
        } else {
            currentSet.add(imageItem)
        }
        _bookmarkedItems.value = currentSet
    }
}