package com.example.memory.timer

import android.os.CountDownTimer
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @author user
 * @date 30.07.2021
 */
class MainActivityViewModel: ViewModel() {
    lateinit var timer: CountDownTimer

    private val _seconds = MutableLiveData<Int>()
    var finished = MutableLiveData<Boolean>()
    var timerValue = MutableLiveData<Long>()

    fun seconds(): LiveData<Int> {
        return _seconds
    }

    fun startTimer() {
        timer = object : CountDownTimer(timerValue.value!!.toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val timeLeft = millisUntilFinished / 1000
                _seconds.value = timeLeft.toInt()
            }

            override fun onFinish() {
                finished.value = true
            }
        }.start()
    }

    fun stopTimer() {
        timer.cancel()
    }
}