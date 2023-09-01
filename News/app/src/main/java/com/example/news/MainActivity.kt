package com.example.news

import android.app.ProgressDialog.show
import android.content.res.Configuration
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.news.databinding.ActivityMainBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val titleFragment = TitleFragment()
    private val detailFragment = DetailFragment()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val date = binding.mainTvDate
        date.text = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE)
        Log.d("tag","onCreate")

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_frame, titleFragment)
                .commit()
        }
//        } else {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.main_frame, titleFragment)
//                .replace(R.id.detail_frame, detailFragment)
//                .commit()
//        }
    }
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_frame, titleFragment)
                .replace(R.id.detail_frame, detailFragment)
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_frame, titleFragment)
                .commit()
        }
    }
}
//if (savedInstanceState == null)