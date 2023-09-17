package com.example.searchmedia.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.searchmedia.data.model.ImageItem
import com.example.searchmedia.data.model.Media
import com.example.searchmedia.data.repository.ImageSearchRepository

class ImageDeliverViewModel : ViewModel() {

    private val _bookmarkedItems = MutableLiveData<List<ImageItem>>()
    val bookmarkedItems: LiveData<List<ImageItem>> get() = _bookmarkedItems
    fun add(imageItem: ImageItem) {
        val currentList = _bookmarkedItems.value?.toMutableList() ?: mutableListOf()
        currentList.add(imageItem)
        _bookmarkedItems.value = currentList
    }
    fun remove(imageItem: ImageItem) {
        val currentList = _bookmarkedItems.value?.toMutableList() ?: mutableListOf()
        currentList.remove(imageItem)
        _bookmarkedItems.value = currentList
    }
}