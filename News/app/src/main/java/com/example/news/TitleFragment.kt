package com.example.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.news.databinding.FragmentTitleBinding

class TitleFragment : Fragment(R.layout.fragment_title) {

    private lateinit var binding : FragmentTitleBinding
    private lateinit var rv : RecyclerView
    private lateinit var adapter : NewsAdapter
    private lateinit var newsList : ArrayList<NewsItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentTitleBinding.inflate(layoutInflater)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_title, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

//    companion object {
//        fun newInstance(param1: String, param2: String) =
//            TitleFragment().apply {
//                arguments = Bundle().apply {
//                    putString()
//                    putString()
//                }
//            }
//    }
}
