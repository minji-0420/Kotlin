package com.example.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.news.databinding.NewsItemListBinding

class NewsAdapter(val newsItems: MutableList<NewsItem>) : RecyclerView.Adapter<NewsAdapter.Holder>() {

    interface OnItemClickListener {
        fun onItemClick(data: NewsItem, position: Int)
    }

    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.Holder {
        val binding = NewsItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: NewsAdapter.Holder, position: Int) {
        val item = newsItems[position]
        holder.image.setImageResource(item.image)
        holder.num.text = item.num.toString()
        holder.title.text = item.title
        holder.detail.text = item.detail
    }

    override fun getItemCount(): Int {
        return newsItems.size
    }

    inner class Holder(val binding: NewsItemListBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener?.onItemClick(newsItems[position], position)
                }
            }
        }

        val image = binding.rvIvImage
        val num = binding.rvTvNum
        val title = binding.rvTvNewsTitle
        val detail = binding.rvTvNewsDetail
    }
}