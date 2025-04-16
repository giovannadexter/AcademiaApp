package com.example.academiaapp.ui

import android.os.Bundle
import android.os.CountDownTimer
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.academiaapp.R

class TimerFragment : Fragment() {

    private lateinit var textViewTimer: TextView
    private lateinit var editMinutes: EditText
    private lateinit var editSeconds: EditText
    private lateinit var buttonStart: Button
    private lateinit var buttonPause: Button
    private lateinit var buttonReset: Button

    private var timer: CountDownTimer? = null
    private var timeLeftInMillis: Long = 0
    private var isRunning = false
    private var initialTimeMillis: Long = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_timer, container, false)

        textViewTimer = view.findViewById(R.id.textViewTimer)
        editMinutes = view.findViewById(R.id.editMinutes)
        editSeconds = view.findViewById(R.id.editSeconds)
        buttonStart = view.findViewById(R.id.buttonStart)
        buttonPause = view.findViewById(R.id.buttonPause)
        buttonReset = view.findViewById(R.id.buttonReset)

        updateTimerText()

        buttonStart.setOnClickListener {
            if (!isRunning) {
                val minutes = editMinutes.text.toString()
                val seconds = editSeconds.text.toString()

                val totalMillis = convertToMillis(minutes, seconds)
                if (totalMillis > 0) {
                    timeLeftInMillis = totalMillis
                    initialTimeMillis = totalMillis
                    startTimer()
                } else {
                    Toast.makeText(context, "Informe tempo válido!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        buttonPause.setOnClickListener {
            if (isRunning) pauseTimer()
        }

        buttonReset.setOnClickListener {
            resetTimer()
        }

        return view
    }

    private fun convertToMillis(min: String, sec: String): Long {
        val minutes = if (!TextUtils.isEmpty(min)) min.toLong() else 0
        val seconds = if (!TextUtils.isEmpty(sec)) sec.toLong() else 0
        return (minutes * 60 + seconds) * 1000
    }

    private fun startTimer() {
        timer = object : CountDownTimer(timeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                updateTimerText()
            }

            override fun onFinish() {
                isRunning = false
                Toast.makeText(context, "Tempo finalizado!", Toast.LENGTH_SHORT).show()
            }
        }.start()
        isRunning = true
    }

    private fun pauseTimer() {
        timer?.cancel()
        isRunning = false
    }

    private fun resetTimer() {
        pauseTimer()
        timeLeftInMillis = initialTimeMillis
        updateTimerText()
    }

    private fun updateTimerText() {
        val seconds = (timeLeftInMillis / 1000).toInt()
        val minutes = seconds / 60
        val secs = seconds % 60
        val timeFormatted = String.format("%02d:%02d", minutes, secs)
        textViewTimer.text = timeFormatted
    }
}
