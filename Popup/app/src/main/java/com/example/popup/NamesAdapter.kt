package com.example.popup

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class NamesAdapter(val listOfNames: MutableList<String>): RecyclerView.Adapter<NamesAdapter.MyHolder>() {
    var OnItemClickRecyclerView: OnItemClickRecyclerView? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NamesAdapter.MyHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: NamesAdapter.MyHolder, position: Int) {
        val nome = this.listOfNames.get(position)
        holder.tvName.text = nome
    }

    override fun getItemCount(): Int {
        return this.listOfNames.size
    }

    fun add(nome: String){
        this.listOfNames.add(nome)
        this.notifyItemInserted(this.listOfNames.size)
    }

    fun del(position: Int){
        this.listOfNames.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, this.listOfNames.size)
    }

    fun mov(from: Int, to: Int){
        Collections.swap(this.listOfNames, from, to)
        notifyItemMoved(from, to)
    }

    inner class MyHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        var tvName: TextView

        init {
            this.tvName = itemView.findViewById(R.id.tvItemName)

            itemView.setOnClickListener {
                this@NamesAdapter.OnItemClickRecyclerView?.onItemClick(this.adapterPosition)
            }
        }
    }
}