package com.example.searchmedia.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.searchmedia.data.model.ImageItem
import com.example.searchmedia.databinding.ItemListBinding
import com.example.searchmedia.ui.viewmodel.ImageDeliverViewModel

class ImageSearchViewHolder(
    private val binding: ItemListBinding,
    private val imageDeliverViewModel: ImageDeliverViewModel
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.rvIvHeart.setOnClickListener {
            val imageItem = imageDeliverViewModel.bookmarkedItems.value?.get(absoluteAdapterPosition)
            if (imageItem != null) {
                imageDeliverViewModel.add(imageItem)
            }
        }
    }

    fun bind(imageItem: ImageItem) {
        val imageIv = imageItem.thumbnail_url
        val titleTv = imageItem.display_sitename
        val dateTime = if(imageItem.datetime?.isNotEmpty() == true) imageItem.datetime.substring(0,19) else "No Date"

        itemView.apply {
            binding.rvIvMain.load(imageIv)
            binding.rvIvHeart.setImageResource(com.example.searchmedia.R.drawable.sf_iv_heart)
            binding.rvTvName.text = titleTv
            binding.rvTvDate.text = dateTime
        }
    }
}