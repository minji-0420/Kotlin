package com.example.applemarket

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.applemarket.databinding.ActivityDetailBinding
import com.google.android.material.snackbar.Snackbar

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 인텐트로부터 Parcelable 리스트를 가져옴
        val itemList = intent.getParcelableArrayListExtra<ItemList>("itemList") ?: ArrayList()

        if (itemList.isNotEmpty()) {
            val data = itemList[0] // 여기서는 첫 번째 아이템만 가져옴
            val image = data.image
            val title = data.title
            val address = data.address
            val price = data.price
            val detail = data.detail
            val id = data.id

            binding.detailIv.setImageResource(image)
            binding.detailTvTitle.text = title
            binding.detailTvAddress.text = address
            binding.detailTvPrice.text = price
            binding.detailTvDetail.text = detail
            binding.detailTvId.text = id
        }
        binding.detailIvBack.setOnClickListener {
            finish()
        }
        var isHeartFilled: Boolean = false
        binding.detailIvHeart.setOnClickListener {

            if (isHeartFilled) {
                binding.detailIvHeart.setImageResource(R.drawable.detail_heart_fill)
                Snackbar.make(binding.root, "관심 목록에 추가되었습니다.", Snackbar.LENGTH_SHORT).show()
            } else {
                binding.detailIvHeart.setImageResource(R.drawable.detail_heart_empty)
                Snackbar.make(binding.root, "관심 목록에서 제거되었습니다.", Snackbar.LENGTH_SHORT).show()
            }

            isHeartFilled = !isHeartFilled
        }
    }
}