package com.example.searchmedia.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.searchmedia.data.model.ImageItem
import com.example.searchmedia.databinding.ItemListBinding
import com.example.searchmedia.ui.viewmodel.BookmarkViewModel
import com.example.searchmedia.ui.viewmodel.ImageSearchViewModel

class BookmarkRVAdapter(private var bookmarkViewModel: BookmarkViewModel, private var imageSearchViewModel: ImageSearchViewModel) :
    ListAdapter<ImageItem, BookmarkViewHolder>(ImageDiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        return BookmarkViewHolder(
            ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            bookmarkViewModel, imageSearchViewModel)
    }
    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        val image = getItem(position)
        holder.bind(image)
    }
    companion object {
        private val ImageDiffCallBack = object : DiffUtil.ItemCallback<ImageItem>() {
            override fun areItemsTheSame(oldItem: ImageItem, newItem: ImageItem): Boolean {
                return oldItem.image_url == newItem.image_url
            }

            override fun areContentsTheSame(oldItem: ImageItem, newItem: ImageItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}