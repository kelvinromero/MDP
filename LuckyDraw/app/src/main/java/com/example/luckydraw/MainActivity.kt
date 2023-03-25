package com.example.luckydraw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var etWord: EditText
    private lateinit var btnAdd: Button
    private lateinit var btnDraw: Button
    private lateinit var tvLuckyDraw: TextView
    private lateinit var words: MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.etWord = findViewById(R.id.etWord)
        this.btnAdd = findViewById(R.id.btnAdd)
        this.btnDraw = findViewById(R.id.btnDraw)
        this.tvLuckyDraw = findViewById(R.id.tvLuckyDraw)
        this.words = mutableListOf()

        this.btnAdd.setOnClickListener { add() }
        this.btnDraw.setOnClickListener { draw() }
    }

    private fun add() {
        val word = this.etWord.text.toString()
        this.words.add(word)
        this.etWord.text.clear()
    }

    private fun draw() {
        val word = this.words.random()
        this.tvLuckyDraw.text = word
    }
}