package com.example.searchmedia.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.searchmedia.R
import com.example.searchmedia.data.repository.ImageSearchRepositoryImpl
import com.example.searchmedia.ui.adapter.VPAdapter
import com.example.searchmedia.databinding.ActivityMainBinding
import com.example.searchmedia.ui.viewmodel.BookmarkViewModel
import com.example.searchmedia.ui.viewmodel.ImageSearchViewModel
import com.example.searchmedia.ui.viewmodel.ImageSearchViewModelProviderFactory
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater)}
    private lateinit var tabLayout: TabLayout
    private val viewpagerAdapter = VPAdapter(this)
    private val tabTextList = listOf("Search", "Bookmark")
    private val tabIconList = listOf(R.drawable.vp_iv_search, R.drawable.vp_iv_bookmark)
    lateinit var imageSearchViewModel: ImageSearchViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.mainViewPager.adapter = viewpagerAdapter
        tabLayout = binding.mainTabLayout

        TabLayoutMediator(binding.mainTabLayout, binding.mainViewPager) { tab, position ->
            tab.text = tabTextList[position]
            tab.setIcon(tabIconList[position])
        }.attach()

        val imageSearchRepository = ImageSearchRepositoryImpl()
        val imageFactory = ImageSearchViewModelProviderFactory(imageSearchRepository, this)
        imageSearchViewModel = ViewModelProvider(this, imageFactory)[ImageSearchViewModel::class.java]
    }
}