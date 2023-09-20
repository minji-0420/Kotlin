package com.example.searchmedia.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.example.searchmedia.data.model.ImageItem
import com.example.searchmedia.data.model.Media
import com.example.searchmedia.data.repository.ImageSearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ImageSearchViewModel(
    private val imageSearchRepository: ImageSearchRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _searchResult = MutableLiveData<Media>()

    val _searchImageLiveData: MutableLiveData<List<ImageItem>> = _searchResult.map { response ->
        (response.mediaDocuments)
    }.toMutableLiveData()

    val searchImageListLiveData: LiveData<List<ImageItem>> = _searchImageLiveData

    fun itemBookmarked(imageItem: ImageItem)  {
        val items =
        _searchImageLiveData.value?.toMutableList() ?: return
        val pos = items.indexOf(imageItem)
        items.removeAt(pos)
        items.add(pos, imageItem.toggleHeartFilled())
        _searchImageLiveData.value = items
    }

    fun <T> LiveData<T>.toMutableLiveData(): MutableLiveData<T> {
        val mediatorLiveData = MediatorLiveData<T>()
        mediatorLiveData.addSource(this) {
            mediatorLiveData.value = it
        }
        return mediatorLiveData
    }

    fun searchImage(query: String) = viewModelScope.launch(Dispatchers.IO) {
        val response = imageSearchRepository.searchImage(query, "accuracy", 1, 80)
        if (response.isSuccessful) {
            response.body()?.let { body ->
                _searchResult.postValue(body)
            }
        }
    }
    var query = String()
        set(value) {
            field = value
            savedStateHandle[SAVE_STATE_KEY] = value
        }
    init {
        query = savedStateHandle.get<String>(SAVE_STATE_KEY) ?: ""
    }
    companion object {
        private const val SAVE_STATE_KEY = "query"
    }
}