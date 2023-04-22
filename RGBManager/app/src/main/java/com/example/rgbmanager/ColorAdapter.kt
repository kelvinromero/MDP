package com.example.rgbmanager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.Collections

class ColorAdapter(val colors: MutableList<String>): RecyclerView.Adapter<ColorAdapter.MyHolder>() {
    var onItemClickRecyclerView: OnItemClickRecyclerView? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorAdapter.MyHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_recycler, parent, false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: ColorAdapter.MyHolder, position: Int) {
        val color = this.colors.get(position)
        holder.tvNome.text = color
    }

    override fun getItemCount(): Int {
        return this.colors.size
    }

    fun add(color: String) {
        this.colors.add(color)
        this.notifyDataSetChanged()
    }

    fun del(index: Int) {
        this.colors.removeAt(index)
        this.notifyItemRemoved(index)
        this.notifyItemRangeChanged(index, this.colors.size)
    }

    fun mov(from: Int, to: Int) {
        Collections.swap(this.colors, from, to)
        this.notifyItemMoved(from, to)
    }

    inner class MyHolder(item: View) : RecyclerView.ViewHolder(item) {
        var tvNome: TextView

        init {
            this.tvNome = item.findViewById(R.id.tvItemNome)
            item.setOnClickListener({
                this@ColorAdapter.onItemClickRecyclerView?.onItemClick(this.adapterPosition)
            })
        }
    }
}