package com.example.applemarket

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.applemarket.databinding.ListItemBinding


class Adapter(val itemList: MutableList<ItemList>) :
    RecyclerView.Adapter<Adapter.CustomViewHolder>() {
    interface OnItemClickListener {
        fun onItemClick(data: ItemList, position: Int)
    }

    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
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
        if (item.isHeartFilled) {
            holder.heart.setImageResource(R.drawable.detail_heart_fill)
        } else {
            holder.heart.setImageResource(R.drawable.detail_heart_empty)
        }
        holder.reviews.text = item.reviews.toString()
        holder.likes.text = item.likes.toString()

    }
    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class CustomViewHolder(val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnLongClickListener {
                val builder = AlertDialog.Builder(binding.root.context)
                builder.setTitle("상품 삭제")
                builder.setIcon(R.drawable.main_chatting)
                builder.setMessage("상품을 정말로 삭제하시겠습니까?")
                builder.setPositiveButton("확인") { _, _ ->
                    val position = bindingAdapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        removeItem(position)
                    }
                }
                builder.setNegativeButton("취소") { _, _ -> }
                builder.show()
                true
            }

            itemView.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener?.onItemClick(itemList[position], position)
                }
            }
        }
        val image = binding.mainIvProduct
        val name = binding.mainTvName
        val address = binding.mainTvAddress
        val price = binding.mainTvPrice
        val heart = binding.mainIvHeart
        val reviews = binding.mainTvReview
        val likes = binding.mainTvHeart
    }

    private fun removeItem(position: Int) {
        if (position in 0 until itemList.size) {
            itemList.removeAt(position)
            notifyItemRemoved(position)
        }
    }
}