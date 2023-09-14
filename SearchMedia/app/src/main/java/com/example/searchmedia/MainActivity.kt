package com.example.searchmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.searchmedia.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater)}
    private lateinit var tabLayout: TabLayout
    private val viewpagerAdapter = VPAdapter(this)
    private val tabTextList = listOf("Search", "Bookmark")
    private val tabIconList = listOf(R.drawable.vp_iv_search,R.drawable.vp_iv_bookmark)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.mainViewPager.adapter = viewpagerAdapter
        tabLayout = binding.mainTabLayout

        TabLayoutMediator(binding.mainTabLayout, binding.mainViewPager) { tab, position ->
            tab.text = tabTextList[position]
            tab.setIcon(tabIconList[position])
        }.attach()
    }
}