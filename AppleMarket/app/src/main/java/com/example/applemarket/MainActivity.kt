package com.example.applemarket

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.applemarket.databinding.ActivityMainBinding
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataList = mutableListOf<ItemList>()
        dataList.add(
            ItemList(
                R.drawable.sample1,
                "산지 한달 된 선풍기 팝니다",
                "서울 서대문구 창천동",
                getDecimalFormat(1000),
                R.drawable.main_chatting,
                R.drawable.detail_heart_empty,
                25,
                13
            )
        )
        dataList.add(
            ItemList(
                R.drawable.sample2,
                "김치냉장고",
                "인천 계양구 귤현동",
                getDecimalFormat(20000),
                R.drawable.main_chatting,
                R.drawable.detail_heart_empty,
                28,
                8
            )
        )
        dataList.add(
            ItemList(
                R.drawable.sample3,
                "샤넬 카드지갑",
                "수성구 범어동",
                getDecimalFormat(10000),
                R.drawable.main_chatting,
                R.drawable.detail_heart_empty,
                5,
                23
            )
        )
        dataList.add(
            ItemList(
                R.drawable.sample4,
                "금고",
                "해운대구 우제2동",
                getDecimalFormat(10000),
                R.drawable.main_chatting,
                R.drawable.detail_heart_empty,
                17,
                14
            )
        )
        dataList.add(
            ItemList(
                R.drawable.sample5,
                "갤럭시Z플립3 팝니다",
                "연제구 연산제8동",
                getDecimalFormat(150000),
                R.drawable.main_chatting,
                R.drawable.detail_heart_empty,
                9,
                22
            )
        )
        dataList.add(
            ItemList(
                R.drawable.sample6,
                "프라다 복조리백",
                "수원시 영통구 원천동",
                getDecimalFormat(50000),
                R.drawable.main_chatting,
                R.drawable.detail_heart_empty,
                16,
                25
            )
        )
        dataList.add(
            ItemList(
                R.drawable.sample7,
                "울산 동해오션뷰 60평 복층 펜트하우스 1일 숙박권 펜션 힐링 숙소 별장",
                "남구 옥동",
                getDecimalFormat(150000),
                R.drawable.main_chatting,
                R.drawable.detail_heart_empty,
                54,
                142
            )
        )
        dataList.add(
            ItemList(
                R.drawable.sample8,
                "샤넬 탑핸들 가방",
                "동래구 온천제2동",
                getDecimalFormat(180000),
                R.drawable.main_chatting,
                R.drawable.detail_heart_empty,
                7,
                31
            )
        )
        dataList.add(
            ItemList(
                R.drawable.sample9,
                "4행정 엔진분무기 판매합니다.",
                "원주시 명륜2동",
                getDecimalFormat(30000),
                R.drawable.main_chatting,
                R.drawable.detail_heart_empty,
                28,
                7
            )
        )
        dataList.add(
            ItemList(
                R.drawable.sample10,
                "셀린느 버킷 가방",
                "중구 동화동",
                getDecimalFormat(190000),
                R.drawable.main_chatting,
                R.drawable.detail_heart_empty,
                6,
                40
            )
        )

        val adapter = Adapter(dataList)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.setHasFixedSize(true)

        adapter.setOnItemClickListener(object : Adapter.OnItemClickListener {
            override fun onItemClick(data: ItemList, position: Int) {
                // 해당 상품의 디테일 페이지로 이동
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                // 여기서 data를 상세 페이지로 넘길 수 있음 (예: intent.putExtra("itemData", data))
                startActivity(intent)
            }
        }
        )
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("종료하시겠습니까?")
        builder.setPositiveButton("확인", DialogInterface.OnClickListener { _, _ ->
            finish()
        })
        builder.setNegativeButton("취소", DialogInterface.OnClickListener { _, _ ->
        })
        builder.show()
    }
}

fun getDecimalFormat(number: Int): String {
    val decimalFormat = DecimalFormat("#,###")
    val formattedPrice = decimalFormat.format(number)
    return "$formattedPrice 원"
}


