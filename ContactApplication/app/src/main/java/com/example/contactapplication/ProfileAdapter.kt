package com.example.contactapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contactapplication.databinding.FavlistItemBinding
import com.example.contactapplication.databinding.ListItemBinding


class ProfileAdapter(val profileList: ArrayList<Profiles>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    interface ItemClick {
        fun onClick(view: View, position: Int)
    }

    var itemClick: ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            Profiles.VIEW_TYPE_LEFT -> {
                val binding =
                    ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return Holder(binding)
            }

            Profiles.VIEW_TYPE_RIGHT -> {
                val binding =
                    FavlistItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return FavoritesHolder(binding)
            }

            else -> throw RuntimeException("알 수 없는 뷰 타입")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            itemClick?.onClick(it, position)
        }

        val item = profileList[position]
        when (item.viewType) {
            Profiles.VIEW_TYPE_LEFT -> {
                if (holder is Holder) {
                    holder.name.text = item.name
                    holder.phone.text = item.phone
                    holder.image.setImageResource(item.image)
                    holder.cb.isChecked = false
                    holder.cb.setOnClickListener {
                        item.viewType = Profiles.VIEW_TYPE_RIGHT

                        holder.itemView.post {
                            notifyItemChanged(position)

                        }
                    }
                }
            }
            Profiles.VIEW_TYPE_RIGHT -> {
                if (holder is FavoritesHolder) {
                    holder.name.text = item.name
                    holder.phone.text = item.phone
                    holder.image.setImageResource(item.image)
                    holder.heart.setOnClickListener {
                        item.viewType = Profiles.VIEW_TYPE_LEFT

                        holder.itemView.post {
                            notifyItemChanged(position)
                        }
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return profileList.size
    }

    override fun getItemViewType(position: Int): Int {
        return profileList[position].viewType
    }

    inner class Holder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val image = binding.ivUser
        var name = binding.tvName
        var phone = binding.tvPhoneNumber
        var cb = binding.listCb
    }

    inner class FavoritesHolder(val binding: FavlistItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val image = binding.ivUser
        var name = binding.tvName
        var phone = binding.tvPhoneNumber
        var heart = binding.favIvHeart
    }
}