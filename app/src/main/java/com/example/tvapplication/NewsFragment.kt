package com.example.tvapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tvapplication.databinding.FragmentNewsBinding

class NewsFragment : Fragment() {
    private lateinit var binding:FragmentNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsBinding.inflate(inflater,container,false)
        binding.recyclers.layoutManager = GridLayoutManager(requireContext(),3)
        binding.recyclers.adapter = QuestsRecycler(requireContext(),MyObj().list)
        return binding.root
    }


}