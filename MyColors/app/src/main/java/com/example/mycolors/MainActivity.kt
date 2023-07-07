package com.example.mycolors

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var sbRed: SeekBar
    private lateinit var sbGreen: SeekBar
    private lateinit var sbBlue: SeekBar
    private lateinit var tvRed: TextView
    private lateinit var tvGreen: TextView
    private lateinit var tvBlue: TextView
    private lateinit var tvHexColor: TextView
    private lateinit var llColorPreview: LinearLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.sbRed = findViewById(R.id.sbRed)
        this.sbGreen = findViewById(R.id.sbGreen)
        this.sbBlue = findViewById(R.id.sbBlue)
        this.tvRed = findViewById(R.id.tvRed)
        this.tvGreen = findViewById(R.id.tvGreen)
        this.tvBlue = findViewById(R.id.tvBlue)
        this.tvHexColor = findViewById(R.id.tvHexColor)
        this.llColorPreview = findViewById(R.id.llColorPreview)

        this.llColorPreview.setBackgroundColor(this.mixColors())
        this.tvHexColor.setTextColor(this.invertColor(this.mixColors()))
        this.tvHexColor.text = this.getHexColor(this.mixColors())

        this.sbRed.setOnSeekBarChangeListener(OnChangeColor())
        this.sbGreen.setOnSeekBarChangeListener(OnChangeColor())
        this.sbBlue.setOnSeekBarChangeListener(OnChangeColor())
    }

    private fun mixColors(): Int {
        val red = this@MainActivity.sbRed.progress
        val green = this@MainActivity.sbGreen.progress
        val blue = this@MainActivity.sbBlue.progress

        return Color.rgb(red, green, blue)
    }

    private fun invertColor(color: Int): Int {
        val red = 255 - Color.red(color)
        val green = 255 - Color.green(color)
        val blue = 255 - Color.blue(color)

        return Color.rgb(red, green, blue)
    }

    private fun getHexColor(color: Int): String {
        return Integer.toHexString(color)
    }

    inner class OnChangeColor: OnSeekBarChangeListener{
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            val red = this@MainActivity.sbRed.progress
            val green = this@MainActivity.sbGreen.progress
            val blue = this@MainActivity.sbBlue.progress

            this@MainActivity.tvRed.text = red.toString()
            this@MainActivity.tvGreen.text = green.toString()
            this@MainActivity.tvBlue.text = blue.toString()

            this@MainActivity.llColorPreview.setBackgroundColor(this@MainActivity.mixColors())

            this@MainActivity.tvHexColor.setTextColor(this@MainActivity.invertColor(this@MainActivity.mixColors()))
            this@MainActivity.tvHexColor.text = this@MainActivity.getHexColor(this@MainActivity.mixColors())
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {
//            TODO("Not yet implemented")
        }

        override fun onStopTrackingTouch(seekBar: SeekBar?) {
//            TODO("Not yet implemented")
        }

    }
}