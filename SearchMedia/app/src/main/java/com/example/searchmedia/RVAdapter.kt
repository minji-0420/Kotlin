package com.example.searchmedia

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.searchmedia.databinding.ItemListBinding

class RVAdapter(val imageList : MutableList<ImageItem>, val videoList : MutableList<VideoItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
      return ListHolder(binding)
    }
    override fun getItemCount(): Int {
        return (imageList.size + videoList.size)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val listHolder = holder as ListHolder
        holder.heartIv.setImageResource(R.drawable.sf_iv_heart)

        if (position < imageList.size) {
            val posImage = imageList[position]
            holder.titleTv.text = posImage.displaySiteName
            holder.dateTv.text = posImage.dateTime.toString()
            Glide.with(holder.itemView.context)
                .load(posImage.thumbnailUrl)
                .into(listHolder.imageIv)
        } else {
            val posVideo = videoList[position - imageList.size]
            holder.titleTv.text = posVideo.title
            holder.dateTv.text = posVideo.datetime.toString()
            Glide.with(holder.itemView.context)
                .load(posVideo.thumbnail)
                .into(listHolder.imageIv)
        }
    }
    inner class ListHolder(val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        val rvAdapter = binding.sfRecyclerview
        val imageIv = binding.rvIvMain
        val heartIv = binding.rvIvHeart
        val titleTv = binding.rvTvName
        val dateTv = binding.rvTvDate
    }

}