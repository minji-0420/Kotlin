package com.example.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.news.databinding.FragmentDetailBinding
import com.example.news.databinding.FragmentTitleBinding

class DetailFragment : Fragment() {

    private lateinit var binding : FragmentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(layoutInflater)

        arguments?.let {
            val articleDetail = it.getString("articleDetail")
            val titleDetail = it.getString("titleDetail")
            val imageDetail = it.getInt("imageDetail")
            binding.detailTvDetail.text = articleDetail
            binding.detailTvTitle.text = titleDetail
            binding.detailIvImage.setImageResource(imageDetail)
        }

        return binding.root
    }
}