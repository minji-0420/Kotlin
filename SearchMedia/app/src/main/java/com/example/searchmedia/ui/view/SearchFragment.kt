package com.example.searchmedia.ui.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.searchmedia.ui.adapter.ImageSearchRVAdapter
import com.example.searchmedia.databinding.FragmentSearchBinding
import com.example.searchmedia.ui.viewmodel.BookmarkViewModel
import com.example.searchmedia.ui.viewmodel.ImageSearchViewModel

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var rvAdapter: ImageSearchRVAdapter

    private val imageSearchViewModel: ImageSearchViewModel by lazy {
        ViewModelProvider(requireActivity())[ImageSearchViewModel::class.java]
    }
    private val bookmarkViewModel: BookmarkViewModel by lazy {
        ViewModelProvider(requireActivity())[BookmarkViewModel::class.java]
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
        loadData()
        setUpRecyclerView()
        searchImage()
        imageSearchViewModel.searchImageListLiveData.observe(viewLifecycleOwner) {
            rvAdapter.submitList(it)
        }
    }
    override fun onPause() {
        super.onPause()
        saveData()
        Log.d("onPause", "호출")
    }
    private fun setUpRecyclerView() {
        rvAdapter = ImageSearchRVAdapter(imageSearchViewModel, bookmarkViewModel)
        binding.sfRv.apply {
            setHasFixedSize(true)
            layoutManager =
                GridLayoutManager(requireContext(), 2)
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
    private fun saveData() {
        val pref = requireActivity().getSharedPreferences("pref", 0)
        val edit = pref.edit()
        edit.putString("search", binding.etSearch.text.toString())
        edit.apply()
    }
    private fun loadData() {
        val pref = requireActivity().getSharedPreferences("pref", 0)
        binding.etSearch.setText(pref.getString("search",""))
    }
}