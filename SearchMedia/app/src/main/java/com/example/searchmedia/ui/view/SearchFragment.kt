package com.example.searchmedia.ui.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.searchmedia.data.model.ImageItem
import com.example.searchmedia.ui.adapter.RVAdapter
import com.example.searchmedia.databinding.FragmentSearchBinding
import com.example.searchmedia.ui.viewmodel.ImageSearchViewModel

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var rvAdapter: RVAdapter
    private lateinit var imageSearchViewModel: ImageSearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageSearchViewModel = (activity as MainActivity).imageSearchViewModel

        setUpRecyclerView()
        searchImage()

        imageSearchViewModel.searchResult.observe(viewLifecycleOwner) { response ->
            val image: List<ImageItem> = response.mediaDocuments
            rvAdapter.submitList(image)
        }
    }

    private fun setUpRecyclerView() {
        rvAdapter = RVAdapter()
        binding.sfRv.apply {
            setHasFixedSize(true)
            layoutManager =
                GridLayoutManager(requireContext(), 2)
//            addItemDecoration(
//                DividerItemDecoration(
//                    requireContext(),
//                    DividerItemDecoration.VERTICAL
//                )
//            )
            adapter = rvAdapter
        }
    }

    private fun searchImage() {
        var startTime = System.currentTimeMillis()
        var endTime: Long

        binding.etSearch.text =
            Editable.Factory.getInstance().newEditable(imageSearchViewModel.query)

        binding.etSearch.addTextChangedListener { text: Editable? ->
            endTime = System.currentTimeMillis()
            if (endTime - startTime >= 100L) {
                text?.let {
                    val query = it.toString().trim()
                    if (query.isNotEmpty()) {
                        imageSearchViewModel.searchImage(query)
                        imageSearchViewModel.query = query
                    }
                }
            }
            startTime = endTime
        }
    }
}