package com.example.adultifyandroid.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.adultifyandroid.R
import com.example.adultifyandroid.gameserver.World

internal class WorldAdapter(private var itemsList: List<World>, private var context: Context) :
RecyclerView.Adapter<WorldAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var nameTextView: TextView = view.findViewById(R.id.name)
        var citizensRecyclerView: RecyclerView = view.findViewById(R.id.recycler_view)

//        init {
//            citizensRecyclerView.layoutManager = LinearLayoutManager(context)
//        }

        fun bind(world: World) {
            citizensRecyclerView.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.VERTICAL,false)
            citizensRecyclerView.adapter = CitizenAdapter(world.citizens)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.world_row_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WorldAdapter.MyViewHolder, position: Int) {
        val item = itemsList[position]
        holder.nameTextView.text = item.name

        holder.bind(item)
    }
    override fun getItemCount(): Int {
        return itemsList.size
    }
}