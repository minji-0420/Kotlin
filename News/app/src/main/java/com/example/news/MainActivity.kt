package com.example.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.news.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.apply {
            mainFrame.setOnClickListener {
                setFragment(DetailFragment())
            }
        }
    }

    private fun setFragment(frag: Fragment) {
        supportFragmentManager.commit {
            replace(R.id.main_frame, frag)
            setReorderingAllowed(true)
            addToBackStack("")
        }
    }
}
