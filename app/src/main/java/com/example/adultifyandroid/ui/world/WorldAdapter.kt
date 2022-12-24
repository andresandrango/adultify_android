package com.example.adultifyandroid.ui.world

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.adultifyandroid.R
import com.example.adultifyandroid.gameserver.Citizen
import com.example.adultifyandroid.gameserver.GameService
import com.example.adultifyandroid.gameserver.World
import com.example.adultifyandroid.ui.citizen.CitizenAdapterFactory
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.*


class WorldAdapter @AssistedInject constructor(
    private var gameService: GameService,
    private var citizenAdapterFactory: CitizenAdapterFactory,
    @Assisted private var itemsList: MutableList<World>,
    @Assisted private var context: Context) :
    RecyclerView.Adapter<WorldAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var nameTextView: TextView = view.findViewById(R.id.name)
        var citizensRecyclerView: RecyclerView = view.findViewById(R.id.recycler_view)

        fun bind(world: World) {
            citizensRecyclerView.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.VERTICAL,false)
            citizensRecyclerView.adapter = citizenAdapterFactory.create(world.citizens as MutableList<Citizen>)
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

    // Removes the item from the list iff it was successful at deleting it from the database
    fun deleteItem(position: Int) {

        val w = itemsList.get(position)
        CoroutineScope(Dispatchers.IO).launch {

            val response = gameService.api.deleteWorld(w.id)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    itemsList.removeAt(position)
                    notifyItemRemoved(position)
                } else {
                    Log.e("RETROFIT_ERROR", response.code().toString())
                }
            }
        }
    }
}