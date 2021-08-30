package com.demirli.a33wordfrequencyexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class Adapter(var resultList: List<Map.Entry<String,Int>>): RecyclerView.Adapter<Adapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = resultList.size

    override fun onBindViewHolder(holder: Adapter.ViewHolder, position: Int) {
        holder.word.text = resultList[position].key
        holder.frequency.text = resultList[position].value.toString()

    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val word = view.findViewById<TextView>(R.id.word_tv)
        val frequency = view.findViewById<TextView>(R.id.frequency_tv)
    }
}




