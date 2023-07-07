package com.example.popup

import android.content.DialogInterface
import android.content.DialogInterface.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var rvNames: RecyclerView
    private lateinit var fabAdd: FloatingActionButton
    private var namesList = mutableListOf<String>()
    private lateinit var etName: EditText
    private lateinit var tts: TextToSpeech

    init {
        this.namesList.add("John")
        this.namesList.add("Mary")
        this.namesList.add("Peter")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.rvNames = findViewById(R.id.rvNames)
        this.fabAdd = findViewById(R.id.fabAdd)

        this.fabAdd.setOnClickListener{ add() }

        this.rvNames.adapter = NamesAdapter(this.namesList)
        (this.rvNames.adapter as NamesAdapter).OnItemClickRecyclerView = OnItemClick()
        ItemTouchHelper(OnSwipe()).attachToRecyclerView(this.rvNames)
        this.tts = TextToSpeech(this, null)
    }

    fun add(){
        this.etName = EditText(this)
        val builder = AlertDialog.Builder(this).apply {
            setTitle("Add a name")
            setMessage("Type a name")
            setView(this@MainActivity.etName)
            setPositiveButton("Save", OnClick())
            setNegativeButton("Cancel", null)
        }
        builder.create().show()
    }

    inner class OnClick: OnClickListener{
        override fun onClick(dialog: DialogInterface?, which: Int) {
            val nome = this@MainActivity.etName.text.toString()
            (this@MainActivity.rvNames.adapter as NamesAdapter).add(nome)
        }
    }

    inner class OnItemClick: OnItemClickRecyclerView{
        override fun onItemClick(position: Int) {
            val nome = this@MainActivity.namesList.get(position)
            this@MainActivity.tts.speak(nome, TextToSpeech.QUEUE_FLUSH, null, null)
        }
    }


    inner class OnSwipe : ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.DOWN or ItemTouchHelper.UP,
        ItemTouchHelper.START or ItemTouchHelper.END
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            (this@MainActivity.rvNames.adapter as NamesAdapter).mov(viewHolder.adapterPosition, target.adapterPosition)
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            (this@MainActivity.rvNames.adapter as NamesAdapter).del(viewHolder.adapterPosition)
        }
    }
}