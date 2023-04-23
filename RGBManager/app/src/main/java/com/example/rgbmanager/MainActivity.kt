package com.example.rgbmanager

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var rvColors: RecyclerView
    private lateinit var fabAddColor: FloatingActionButton
    private var colors: MutableList<RGBColor> = mutableListOf()
    private lateinit var editColorResult: ActivityResultLauncher<Intent>

    init {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.rvColors = findViewById(R.id.rvColors)
        this.fabAddColor = findViewById(R.id.fabAddColor)

        this.rvColors.adapter = ColorAdapter(this.colors)
        (this.rvColors.adapter as ColorAdapter).onItemClickRecyclerView = OnItemClick()

        ItemTouchHelper(OnSwipe()).attachToRecyclerView(this.rvColors)

        val addColorResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if (it.resultCode == RESULT_OK) {
                val color = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    it.data?.getSerializableExtra("COLOR", RGBColor::class.java)
                } else {
                    it.data?.getSerializableExtra("COLOR") as RGBColor
                } as RGBColor

                this.colors.add(color)
            }
        }

        this.editColorResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                val color = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    it.data?.getSerializableExtra("COLOR", RGBColor::class.java)
                } else {
                    it.data?.getSerializableExtra("COLOR") as RGBColor
                } as RGBColor

                val position = it.data?.getIntExtra("POSITION", 0) ?: 0

                this@MainActivity.colors[position] = color
            }
        }

        this.fabAddColor.setOnClickListener {
            val intent = Intent(this, ColorFormActivity::class.java).apply {
                putExtra("COLOR", RGBColor("", 0, 0, 0))
            }
            addColorResult.launch(intent)
        }
    }



    inner class OnItemClick: OnItemClickRecyclerView() {
        override fun onItemClick(position: Int) {
            val color = this@MainActivity.colors.get(position)

            val intent = Intent(this@MainActivity, ColorFormActivity::class.java).apply {
                putExtra("COLOR", color)
            }

            this@MainActivity.editColorResult.launch(intent)
        }
    }

    inner class OnSwipe: ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.DOWN or ItemTouchHelper.UP,
        ItemTouchHelper.START or ItemTouchHelper.END
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder,
        ): Boolean {
            (this@MainActivity.rvColors.adapter as ColorAdapter).mov(
                viewHolder.adapterPosition,
                target.adapterPosition
            )
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            (this@MainActivity.rvColors.adapter as ColorAdapter).del(viewHolder.adapterPosition)
        }
    }
}