package com.example.lifecycle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.lifecycle.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("activity1", "onCreate")
        setContentView(binding.root)

        showToast("LIFE CYCLE", Toast.LENGTH_LONG)

        binding.btnAct.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        binding.btnFrag.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, FirstFragment())
                .commit()
        }

        binding.btnRemove.setOnClickListener {
            supportFragmentManager.findFragmentById(R.id.frameLayout)?.let { it1 ->
                supportFragmentManager.beginTransaction()
                    .remove(it1)
                    .commit()
            }
        }
    }
    override fun onStart() {
        super.onStart()

        Log.d("activity1", "onStart")
    }
    override fun onResume() {
        super.onResume()

        Log.d("activity1", "onResume")
    }
    override fun onPause() {
        super.onPause()

        Log.d("activity1", "onPause")
    }
    override fun onStop() {
        super.onStop()

        Log.d("activity1", "onStop")
    }
    override fun onDestroy() {
        super.onDestroy()

        Log.d("activity1", "onDestroy")
    }
}