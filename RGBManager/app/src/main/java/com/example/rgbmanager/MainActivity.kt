package com.example.rgbmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var rvColors: RecyclerView
    private lateinit var fabAddColor: FloatingActionButton
    private var colors: MutableList<String>

    init {
        this.colors = mutableListOf()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.rvColors = findViewById(R.id.rvColors)
        this.fabAddColor = findViewById(R.id.fabAddColor)

        val adapter = ColorAdapter(this.colors)
        adapter.onItemClickRecyclerView = OnItemClick()
        this.rvColors.adapter = adapter

        ItemTouchHelper(OnSwipe()).attachToRecyclerView(this.rvColors)
    }

    inner class OnItemClick: OnItemClickRecyclerView() {
        override fun onItemClick(position: Int) {
            val color = this@MainActivity.colors.get(position)
            Toast.makeText(this@MainActivity, color, Toast.LENGTH_SHORT).show()
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