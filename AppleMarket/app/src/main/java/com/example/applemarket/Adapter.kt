package com.example.applemarket
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.applemarket.databinding.ListItemBinding


class Adapter(val itemList: MutableList<ItemList>) : RecyclerView.Adapter<Adapter.CustomViewHolder>()
{
    interface OnItemClickListener{
        fun onItemClick(data: ItemList, position: Int)
    }
    private var listener : OnItemClickListener? = null

    fun setOnItemClickListener(listener : OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.CustomViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: Adapter.CustomViewHolder, position: Int) {
        val item = itemList[position]
        holder.image.setImageResource(item.image)
        holder.name.text = item.title
        holder.address.text = item.address
        holder.price.text = item.price
        holder.chatting.setImageResource(item.chatting)
        holder.heart.setImageResource(item.heart)
        holder.reviews.text = item.reviews.toString()
        holder.likes.text = item.likes.toString()

        holder.itemView.setOnClickListener {
            listener?.onItemClick(item, position)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class CustomViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val image = binding.mainIvProduct
        val name = binding.mainTvName
        val address = binding.mainTvAddress
        val price = binding.mainTvPrice
        val chatting = binding.mainIvChatiing
        val heart = binding.mainIvHeart
        val reviews = binding.mainTvReview
        val likes = binding.mainTvHeart
    }
}