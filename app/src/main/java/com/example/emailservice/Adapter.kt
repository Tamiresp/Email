package com.example.emailservice

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter (private val items: MutableList<Email>) : RecyclerView.Adapter<MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val viewHolder = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return MyViewHolder(viewHolder)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item: Email = items[position]
        holder.title.text = item.title
        holder.subject.text = item.subject
    }

    fun addItem(item: Email){
        items.add(item)
        notifyItemInserted(itemCount)
    }
}
class MyViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
    val title: TextView = itemView.findViewById(R.id.title)
    val subject: TextView = itemView.findViewById(R.id.subject)
}