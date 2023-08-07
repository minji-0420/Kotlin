package com.example.introducemyself

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import kotlin.random.Random

class HomeActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val imageArray = arrayOf(
            R.drawable.home_image1,
            R.drawable.home_image2,
            R.drawable.home_image3,
            R.drawable.home_image4,
            R.drawable.home_image5
        )

        val randomImageResource = imageArray[Random.nextInt(imageArray.size)]
        val imageHome = findViewById<ImageView>(R.id.imageHome)
        imageHome.setImageResource(randomImageResource)

        val id = intent.getStringExtra("idFromSignInActivity")
        val cbID = findViewById<TextView>(R.id.cb_id)
        cbID.text = "아이디 : $id"

        val btnFinish = findViewById<Button>(R.id.btn_finish)
        btnFinish.setOnClickListener{
            finish()
        }
    }

}