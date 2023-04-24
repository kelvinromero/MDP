package com.example.rgbmanager

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class ColorFormActivity : AppCompatActivity() {
    private lateinit var color: RGBColor
    private lateinit var etColor: EditText
    private lateinit var etRed: EditText
    private lateinit var etGreen: EditText
    private lateinit var etBlue: EditText
    private lateinit var btnSaveColor: Button
    private lateinit var btnCancel: Button
    private var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_form)

        this.color = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("COLOR", RGBColor::class.java)
        } else {
            intent.getSerializableExtra("COLOR")
        } as RGBColor

        this.position = intent.getIntExtra("POSITION", 0)

        this.etColor = findViewById(R.id.etColorName)
        this.etRed = findViewById(R.id.etRed)
        this.etGreen = findViewById(R.id.etGreen)
        this.etBlue = findViewById(R.id.etBlue)
        this.btnSaveColor = findViewById(R.id.btSaveColor)
        this.btnCancel = findViewById(R.id.btCancelColor)

        if (this.color.name != "") {
            fillForm()
        }

        this.btnSaveColor.setOnClickListener { saveColor() }
        this.btnCancel.setOnClickListener { finish() }
    }

    private fun fillForm() {
        this.etColor.setText(color.name)
        this.etRed.setText(color.red.toString())
        this.etGreen.setText(color.green.toString())
        this.etBlue.setText(color.blue.toString())
    }

    private fun saveColor() {
        val colorName = this.etColor.text.toString()
        val red = this.etRed.text.toString().toInt()
        val green = this.etGreen.text.toString().toInt()
        val blue = this.etBlue.text.toString().toInt()
        val color = RGBColor(colorName, red, green, blue)
        val intent = Intent().apply {
            putExtra("COLOR", color)
            putExtra("POSITION", this@ColorFormActivity.position)
        }

        setResult(RESULT_OK, intent)
        finish()
    }
}