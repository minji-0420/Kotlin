package com.example.introducemyself

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import kotlin.random.Random

class HomeActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val imageArray = arrayOf(
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5
        )

        val randomImageResource = imageArray[Random.nextInt(imageArray.size)]
        val imageHome = findViewById<ImageView>(R.id.imageHome)
        imageHome.setImageResource(randomImageResource)

        val id = intent.getStringExtra("idFromSignInActivity")
        val cbID = findViewById<CheckBox>(R.id.cb_id)
        cbID.text = "ID : $id"

        val btnFinish = findViewById<Button>(R.id.btn_finish)
        btnFinish.setOnClickListener{
            finish()
        }
    }

}