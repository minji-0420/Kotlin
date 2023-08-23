package com.example.netcha_minji

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MyPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage)

        val back_mypage = findViewById<ImageView>(R.id.back_mypage)
        back_mypage.setOnClickListener {
            finish()
        }
    }
}