package com.example.searchmedia.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.searchmedia.data.model.ImageItem
import com.example.searchmedia.databinding.FragmentBookmarkBinding
import com.example.searchmedia.ui.adapter.ImageSearchRVAdapter
import com.example.searchmedia.ui.viewmodel.BookmarkViewModel
import com.example.searchmedia.ui.viewmodel.ImageSearchViewModel

class BookmarkFragment : Fragment() {

    private var _binding: FragmentBookmarkBinding? = null
    private val binding get() = _binding!!
    private lateinit var rvAdapter: ImageSearchRVAdapter
    private val imageSearchViewModel : ImageSearchViewModel by activityViewModels()
    private val bookmarkViewModel: BookmarkViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()

        bookmarkViewModel.bookmarkedItems.observe(viewLifecycleOwner) {
            rvAdapter.submitList(it)
        }
    }
    private fun setUpRecyclerView() {
        rvAdapter = ImageSearchRVAdapter(imageSearchViewModel,bookmarkViewModel)
        binding.sfRv.apply {
            setHasFixedSize(true)
            layoutManager =
                GridLayoutManager(requireContext(), 2)
            adapter = rvAdapter
        }
    }
}