package com.example.applemarket

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.applemarket.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 인텐트로부터 Parcelable 리스트를 가져옴
        val itemList = intent.getParcelableArrayListExtra<ItemList>("itemList") ?: ArrayList()

        // itemList에서 데이터 추출
        if (itemList != null && itemList.isNotEmpty()) {
            val data = itemList[0] // 여기서는 첫 번째 아이템만 가져옴
            val image = data.image
            val title = data.title
            val address = data.address
            val price = data.price
            val detail = data.detail
            val id = data.id

            // 데이터를 뷰에 설정
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
    }
}