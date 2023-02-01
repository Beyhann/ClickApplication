package com.example.click

import android.os.Bundle
import android.text.format.DateUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.click.databinding.FragmentGameBinding

class GameFragment : Fragment() {

    private lateinit var binding : FragmentGameBinding
    private lateinit var viewmodel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //hide action bar
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()

        // Inflate the layout for this fragment
      //  return inflater.inflate(R.layout.fragment_game, container, false)
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_game, container, false)
        viewmodel = ViewModelProvider(this).get(GameViewModel::class.java)

        //set up observers
        viewmodel.currentTime.observe(viewLifecycleOwner, Observer{time -> binding.gamefrgtvtimer.text = DateUtils.formatElapsedTime(time)})
        viewmodel.currentButton.observe(viewLifecycleOwner, Observer { btn -> moveButton(btn) })
        viewmodel.score.observe(viewLifecycleOwner, Observer { score ->  binding.gamefrgtvscore.text =score.toString()})
        viewmodel.scoreColor.observe(viewLifecycleOwner, Observer { color -> changescorecolor(color) })
        viewmodel.gameFinished.observe(viewLifecycleOwner, Observer { finish -> if(finish) gameOver() })

        //set slick listeners
        binding.gamefrgbtn1.setOnClickListener { viewmodel.gainpoint() }
        binding.gamefrgbtn2.setOnClickListener { viewmodel.gainpoint()  }
        binding.gamefrgbtn3.setOnClickListener { viewmodel.gainpoint()  }
        binding.gamefrgbtn4.setOnClickListener { viewmodel.gainpoint()  }
        binding.gameFragmentLoGame.setOnClickListener{ viewmodel.losepoint()}


        return binding.root
    }

    //only show given button
    private fun moveButton(button:Int){

        binding.gamefrgbtn1.visibility = View.INVISIBLE
        binding.gamefrgbtn2.visibility = View.INVISIBLE
        binding.gamefrgbtn3.visibility = View.INVISIBLE
        binding.gamefrgbtn4.visibility = View.INVISIBLE

        when(button){

            1->  binding.gamefrgbtn1.visibility = View.VISIBLE
            2->  binding.gamefrgbtn2.visibility = View.VISIBLE
            3->  binding.gamefrgbtn3.visibility = View.VISIBLE
            else -> binding.gamefrgbtn4.visibility = View.VISIBLE
        }

    }

    //change score color
    private fun changescorecolor(color: String){

        if(color =="green"){
            binding.gamefrgtvscore.setTextColor(ContextCompat.getColor(requireContext().applicationContext, R.color.green))
        }
        else{
            binding.gamefrgtvscore.setTextColor(ContextCompat.getColor(requireContext().applicationContext, R.color.red))
        }

    }

    private fun gameOver(){

        findNavController().navigate(GameFragmentDirections.actionGameFragmentToScoreFragment(viewmodel.score.value!!))
    }
}