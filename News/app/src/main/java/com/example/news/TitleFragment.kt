package com.example.news

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.news.databinding.FragmentTitleBinding

class TitleFragment : Fragment(R.layout.fragment_title) {

    private lateinit var binding : FragmentTitleBinding
    private lateinit var rv : RecyclerView
    private lateinit var adapter : NewsAdapter
    private var newsItems = NewsListRepository.getNewsList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTitleBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv = binding.rvNewsItem
        rv.layoutManager = LinearLayoutManager(requireContext())


        adapter = NewsAdapter(newsItems as MutableList<NewsItem>)
        rv.adapter = adapter

        adapter.setOnItemClickListener(object : NewsAdapter.OnItemClickListener {

            override fun onItemClick(data: NewsItem, position: Int) {
                val articleDetail = data.detail
                val titleDetail = data.title
                val imageDetail = data.image
                val detailFragment = DetailFragment()
                val bundle = Bundle()
                bundle.putString("articleDetail", articleDetail)
                bundle.putString("titleDetail", titleDetail)
                bundle.putInt("imageDetail", imageDetail)
                detailFragment.arguments = bundle

                if (isLandScape()) {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.detail_frame, detailFragment)
                        .commit()
                } else {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frame, detailFragment)
                        .addToBackStack(null)
                        .commit()
                }
            }
        })
    }
    private fun isLandScape(): Boolean {
        val context = context
        if (context != null) {
            val configuration = context.resources.configuration
            return configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
        }
        return false
    }
}
