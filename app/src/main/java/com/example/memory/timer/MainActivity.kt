package com.example.memory.timer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        viewModel.seconds().observe(this, Observer {
            text.text = it.toString()
        })

        viewModel.finished.observe(this, Observer {
            if (it) {
                Toast.makeText(this, "Finished", Toast.LENGTH_SHORT).show()
            }
        })

        btnStart.setOnClickListener {
            if (editText.text.length < 1) {
                Toast.makeText(this, "Please set time", Toast.LENGTH_SHORT).show()
            } else {
                var convertedValue = editText.text.toString() + "000"
                viewModel.timerValue.value = convertedValue.toLong()
                viewModel.startTimer()
            }
        }

        btnStop.setOnClickListener {
            viewModel.stopTimer()
            text.text = "0"
            viewModel.finished.value = true
        }

        btnPause.setOnClickListener {
            viewModel.stopTimer()
        }
    }
}