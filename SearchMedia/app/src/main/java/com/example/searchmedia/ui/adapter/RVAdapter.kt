package com.example.searchmedia.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.searchmedia.data.model.ImageItem
import com.example.searchmedia.databinding.ItemListBinding
import com.example.searchmedia.ui.viewmodel.ImageDeliverViewModel

class RVAdapter(private val imageDeliverViewModel: ImageDeliverViewModel) :
    ListAdapter<ImageItem, ImageSearchViewHolder>(ImageDiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageSearchViewHolder {
        val imageDeliverViewModel = ViewModelProvider(parent.context as AppCompatActivity).get(ImageDeliverViewModel::class.java)
        return ImageSearchViewHolder(
            ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            imageDeliverViewModel
        )
    }

    override fun onBindViewHolder(holder: ImageSearchViewHolder, position: Int) {
        val image = currentList[position]
        holder.bind(image)
    }

    companion object {
        private val ImageDiffCallBack = object : DiffUtil.ItemCallback<ImageItem>() {
            override fun areItemsTheSame(oldItem: ImageItem, newItem: ImageItem): Boolean {
                return oldItem.doc_url == newItem.doc_url
            }

            override fun areContentsTheSame(oldItem: ImageItem, newItem: ImageItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}
