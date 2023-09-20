package com.example.searchmedia.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.searchmedia.data.model.ImageItem
import com.example.searchmedia.databinding.ItemListBinding
import com.example.searchmedia.ui.viewmodel.BookmarkViewModel
import com.example.searchmedia.ui.viewmodel.ImageSearchViewModel

class ImageSearchRVAdapter(private var imageSearchViewModel: ImageSearchViewModel, private var bookmarkViewModel: BookmarkViewModel) :
    ListAdapter<ImageItem, ImageSearchViewHolder>(ImageDiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageSearchViewHolder {
        return ImageSearchViewHolder(
            ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            imageSearchViewModel,bookmarkViewModel)
    }

    override fun onBindViewHolder(holder: ImageSearchViewHolder, position: Int) {
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