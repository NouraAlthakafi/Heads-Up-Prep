package com.example.myheadsup

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_celebrity.view.*

class RVcelebrity(var celebrityArray: ArrayList<celebrityList>): RecyclerView.Adapter<RVcelebrity.ItemViewHolder>() {
    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_celebrity, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val aNew = celebrityArray[position]
        holder.itemView.apply{
            tvCelebrity.text = "${aNew.name}\n ${aNew.taboo1}\n ${aNew.taboo2}\n ${aNew.taboo3}"
        }
    }

    override fun getItemCount()= celebrityArray.size
}