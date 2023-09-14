package com.example.searchmedia

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class VPAdapter (fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 2
    }
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> SearchFragment()
            1 -> BookmarkFragment()
            else -> throw IllegalArgumentException("잘못된 위치: $position")
        }
    }
}