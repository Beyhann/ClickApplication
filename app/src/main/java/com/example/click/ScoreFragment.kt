package com.example.click

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.click.databinding.FragmentScoreBinding

class ScoreFragment : Fragment() {

    private lateinit var binding : FragmentScoreBinding
    private lateinit var viewModel: ScoreViewModel
    private lateinit var viewmodelfactory : ScoreViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_score, container, false)

        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_score, container, false)
        viewmodelfactory = ScoreViewModelFactory(ScoreFragmentArgs.fromBundle(requireArguments()).score)
        viewModel = ViewModelProvider(this, viewmodelfactory).get(ScoreViewModel::class.java)

        //set clicklisteners
        binding.scorefrgbtngamble.setOnClickListener { viewModel.gamble() }
        binding.scorefrgbtnplay.setOnClickListener { findNavController().navigate(ScoreFragmentDirections.actionScoreFragmentToGameFragment()) }
        binding.scorefrgbtntitle.setOnClickListener { findNavController().navigate(ScoreFragmentDirections.actionScoreFragmentToTitleFragment()) }

        //set up dataBinding to bind data directly to views
        binding.scoreViewModel= viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

}