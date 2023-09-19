package com.example.searchmedia.ui.adapter

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.searchmedia.data.model.ImageItem
import com.example.searchmedia.databinding.ItemListBinding
import com.example.searchmedia.ui.viewmodel.BookmarkViewModel

class BookmarkViewHolder(
    private val binding: ItemListBinding,
    private val bookmarkViewModel: BookmarkViewModel,
    private val onBookmarkChangedListener: OnBookmarkChangedListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(imageItem: ImageItem) {
        val imageIv = imageItem.thumbnail_url
        val titleTv = imageItem.display_sitename
        val dateTime = if (imageItem.datetime?.isNotEmpty() == true) imageItem.datetime.substring(
            0,
            19
        ) else "No Date"

        itemView.apply {
            binding.rvIvMain.load(imageIv)
            binding.rvTvName.text = titleTv
            binding.rvTvDate.text = dateTime
            binding.rvIvHeart.setImageResource(com.example.searchmedia.R.drawable.sf_iv_heart_fill)
        }
        binding.rvIvHeart.setOnClickListener {
            bookmarkViewModel.toggleBookmark(imageItem)
            onBookmarkChangedListener.onBookmarkChanged(imageItem)
        }
    }
}