package com.example.lifecycle

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lifecycle.databinding.ActivitySecondBinding
import com.example.lifecycle.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private val binding by lazy { FragmentFirstBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("fragment1", "onCreate")
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.d("fragment1", "onCreateView")
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState != null) {
            val edit = savedInstanceState.getString("edit")
            binding.fragTv.setText(edit)
        }
        Log.d("fragment1", "onViewCreated")
    }
    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        Log.d("fragment1", "onViewStateRestored")
    }
    override fun onStart() {
        super.onStart()

        Log.d("fragment1", "onStart")
    }
    override fun onResume() {
        super.onResume()

        Log.d("fragment1", "onResume")
    }
    override fun onPause() {
        super.onPause()

        Log.d("fragment1", "onPause")
    }
    override fun onStop() {
        super.onStop()

        Log.d("fragment1", "onStop")
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val edit = binding.fragTv.text.toString()
        outState.putString("edit", edit)

        Log.d("fragment1", "onSaveInstanceState")
    }
    override fun onDestroyView() {
        super.onDestroyView()

        Log.d("fragment1", "onDestroyView")
    }
    override fun onDestroy() {
        super.onDestroy()

        Log.d("fragment1", "onDestroy")
    }
}