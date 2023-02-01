package com.example.click

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    //count down timer
    private val timer : CountDownTimer

   private val _currentTime = MutableLiveData<Long>()
    val currentTime : LiveData<Long> get() = _currentTime

    private val _score = MutableLiveData<Int>()
    val score : LiveData<Int> get() = _score

    private val _currentButton = MutableLiveData<Int>()
    val currentButton : LiveData<Int> get() = _currentButton

    private val _scoreColor = MutableLiveData<String>()
    val scoreColor : LiveData<String> get() = _scoreColor

    private val _gameFinished = MutableLiveData<Boolean>()
    val gameFinished : LiveData<Boolean> get() = _gameFinished

    //set live data values
    init{
        _score.value =0
        _currentButton.value = (1..4).random()
        _scoreColor.value="green"
        _gameFinished.value = false
        timer = object : CountDownTimer(31000,1000){
            override fun onTick(millisUnitFinished: Long) {
                _currentTime.value = millisUnitFinished / 1000 //divide by thousand to convert to seconds
                _scoreColor.value = "green"
            }

            override fun onFinish() {
                _gameFinished.value = true
            }
        }

        //start the timer
        timer.start()
    }

    //gain point cuz button clicked
    fun gainpoint(){

        _score.value = _score.value?.plus(1)
        _currentButton.value = (1..4).random()
        _scoreColor.value = "green"
    }

    //lose point cuz missed clicking button
    fun losepoint(){
        _score.value = _score.value?.plus(-1)
        _currentButton.value = (1..4).random()
        _scoreColor.value = "red"
    }

}