package com.example.click

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.click.databinding.FragmentTitleBinding


class TitleFragment : Fragment() {

    private lateinit var binding: FragmentTitleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
      //  return inflater.inflate(R.layout.fragment_title, container, false)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_title, container,false)
        binding.titlefrgbtnplay.setOnClickListener { findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToGameFragment()) }
        return binding.root
    }


}