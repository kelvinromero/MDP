package com.example.gossipgame

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView

class GuessIfTrueActivity : AppCompatActivity() {
    private lateinit var gossip: Gossip
    private lateinit var tvGossipDescription: TextView
    private lateinit var btnIsTrue: Button
    private lateinit var btnIsFalse: Button
    private lateinit var pbTimer: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guess_if_true)

        this.gossip = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("GOSSIP", Gossip::class.java)
        } else {
            intent.getSerializableExtra("GOSSIP")
        } as Gossip

        this.tvGossipDescription = findViewById(R.id.tvGossipDescription)
        this.btnIsTrue = findViewById(R.id.btnGuessTrue)
        this.btnIsFalse = findViewById(R.id.btnGuessFalse)
        this.pbTimer = findViewById(R.id.pbTimer)

        this.tvGossipDescription.text = gossip.description

        this.btnIsTrue.setOnClickListener { guessTrue() }
        this.btnIsFalse.setOnClickListener { guessFalse() }

        startTimer()
    }

    private fun guessTrue() {
        if (this.gossip.isTrue) {
            setResult(RESULT_OK)
        }
        finish()
    }

    private fun guessFalse() {
        if (!this.gossip.isTrue) {
            setResult(RESULT_OK)
        }
        finish()
    }

    private fun startTimer() {
        Thread{
            while (this.pbTimer.progress < 100){
                this.pbTimer.progress += 1
                Thread.sleep(100)
            }
            setResult(RESULT_CANCELED)
            finish()
        }.start()
    }
}