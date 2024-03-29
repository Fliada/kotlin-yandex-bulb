package com.example.kotlin_yandex_bulb.presenter.ui

import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_yandex_bulb.data.ColorData

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private val list = mutableListOf<ColorData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val context = parent.context
        val textView = TextView(context)
        return MainViewHolder(textView)
    }

    override fun getItemCount(): Int =
        list.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.textView.text = list[position].color
    }

    fun submitList(list: List<ColorData>) {
        with(this.list) {
            clear()
            addAll(list)
        }
        notifyDataSetChanged()
    }

    class MainViewHolder(
        val textView: TextView
    ) : RecyclerView.ViewHolder(textView)
}
