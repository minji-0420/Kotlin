package com.example.searchmedia.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.searchmedia.databinding.FragmentBookmarkBinding
import com.example.searchmedia.databinding.FragmentSearchBinding
import com.example.searchmedia.ui.adapter.ImageSearchRVAdapter
import com.example.searchmedia.ui.viewmodel.ImageSearchViewModel

class BookmarkFragment : Fragment() {

    private var _binding: FragmentBookmarkBinding? = null
    private val binding get() = _binding!!

    private lateinit var imageSearchViewModel: ImageSearchViewModel
    private lateinit var rvAdapter: ImageSearchRVAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageSearchViewModel = (activity as MainActivity).imageSearchViewModel

        setUpRecyclerView()

        imageSearchViewModel.bookmarkedItems.observe(viewLifecycleOwner) {
            rvAdapter.submitList(it)
        }
    }
    private fun setUpRecyclerView() {
        rvAdapter = ImageSearchRVAdapter()
        binding.sfRv.apply {
            setHasFixedSize(true)
            layoutManager =
                GridLayoutManager(requireContext(), 2)
            adapter = rvAdapter
        }
    }
}