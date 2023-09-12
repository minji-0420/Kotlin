package com.example.lifecycle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.lifecycle.databinding.ActivityMainBinding
import com.example.lifecycle.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySecondBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnSecond.setOnClickListener {
           finish()
        }

        Log.d("activity2", "onCreate")
    }
    override fun onStart() {
        super.onStart()

        Log.d("activity2", "onStart")
    }
    override fun onResume() {
        super.onResume()

        Log.d("activity2", "onResume")
    }
    override fun onPause() {
        super.onPause()

        Log.d("activity2", "onPause")
    }
    override fun onStop() {
        super.onStop()

        Log.d("activity2", "onStop")
    }
    override fun onDestroy() {
        super.onDestroy()

        Log.d("activity2", "onDestroy")
    }
}