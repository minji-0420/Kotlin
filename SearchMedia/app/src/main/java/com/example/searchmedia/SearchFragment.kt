package com.example.searchmedia

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.searchmedia.databinding.FragmentBookmarkBinding
import com.example.searchmedia.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private val binding: FragmentSearchBinding by lazy { FragmentSearchBinding.inflate(layoutInflater)}
    private lateinit var rv : RecyclerView
    private lateinit var rvAdapter: RVAdapter
    private var imageList = mutableListOf<ImageItem>()
    private var videoList = mutableListOf<VideoItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv = binding.sfRv
        rv.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        rvAdapter = RVAdapter(imageList, videoList)
        rv.adapter = rvAdapter
    }

    private fun setUpParameter(): HashMap<String, String> {
        return hashMapOf(

        )
    }
}