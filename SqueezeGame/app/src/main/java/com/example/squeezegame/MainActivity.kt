package com.example.squeezegame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.squeezegame.game.SquezeGame

class MainActivity : AppCompatActivity() {

    private lateinit var tvMinNumber: TextView
    private lateinit var tvMaxNumber: TextView
    private lateinit var tvRandNumber: TextView
    private lateinit var tvStatus: TextView
    private lateinit var btnGuess: Button
    private lateinit var btnClear: Button
    private lateinit var etGuess: EditText
    private lateinit var game: SquezeGame

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.tvMinNumber = findViewById(R.id.tvMinNumber)
        this.tvMaxNumber = findViewById(R.id.tvMaxNumber)
        this.tvRandNumber = findViewById(R.id.tvRandNumber)
        this.tvStatus = findViewById(R.id.tvStatus)
        this.btnGuess = findViewById(R.id.btnGuess)
        this.btnClear = findViewById(R.id.btnClear)
        this.etGuess = findViewById(R.id.etGuess)

        this.game = SquezeGame(1, 100)

        this.tvMinNumber.text = this.game.minNumber.toString()
        this.tvMaxNumber.text = this.game.maxNumber.toString()
        this.tvStatus.text = this.game.status.toString()
        this.tvRandNumber.text = this.game.secretNumber.toString()

        this.btnGuess.setOnClickListener {
            this.guess()
        }
    }

    fun guess() {
        val guess: Int = this.etGuess.text.toString().toInt()
        val value = this.game.guess(guess)

        this.tvMinNumber.text = this.game.minNumber.toString()
        this.tvMaxNumber.text = this.game.maxNumber.toString()
        this.tvStatus.text = this.game.status.toString()

        if (value > 0 ) {
            Toast.makeText(this, "Your guess is too high", Toast.LENGTH_SHORT).show()
        } else if (value < 0) {
            Toast.makeText(this, "Your guess is too low", Toast.LENGTH_SHORT).show()
        }
    }

    fun clear() {
        this.etGuess.text.clear()
    }
}