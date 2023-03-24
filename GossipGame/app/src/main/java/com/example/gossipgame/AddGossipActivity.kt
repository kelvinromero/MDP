package com.example.gossipgame

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity

class AddGossipActivity : AppCompatActivity() {
    private lateinit var btnSave: Button
    private lateinit var btnCancel: Button
    private lateinit var rbIsTrue: RadioButton
    private lateinit var etDescription: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gossip)

        this.btnSave = findViewById(R.id.btnSave)
        this.btnCancel = findViewById(R.id.btnCancel)
        this.rbIsTrue = findViewById(R.id.rbIsTrue)
        this.etDescription = findViewById(R.id.etDescription)


        this.btnSave.setOnClickListener { save() }
        this.btnCancel.setOnClickListener { finish() }
    }

    private fun save() {
        val description = this.etDescription.text.toString()
        val isTrue = this.rbIsTrue.isChecked
        val gossip = Gossip(description, isTrue)
        val intent = Intent().apply {
            putExtra("GOSSIP", gossip)
        }

        setResult(RESULT_OK, intent)
        finish()
    }
}