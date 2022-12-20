package com.example.adultifyandroid.ui.citizen;

import android.util.Log
import com.example.adultifyandroid.R

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.adultifyandroid.gameserver.Citizen
import com.example.adultifyandroid.gameserver.GameService
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CitizenAdapter @AssistedInject constructor(
    private var gameService: GameService,
    @Assisted private var itemsList: MutableList<Citizen>) :
    RecyclerView.Adapter<CitizenAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var itemTextView: TextView = view.findViewById(R.id.textView)
    }

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.citizen_row_item, parent, false)
        return MyViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = itemsList[position]
        holder.itemTextView.text = item.name
    }
    override fun getItemCount(): Int {
        return itemsList.size
    }

    // Removes the item from the list iff it was successful at deleting it from the database
    fun deleteItem(position: Int) {

        val w = itemsList[position]
        CoroutineScope(Dispatchers.IO).launch {

            val response = gameService.api.deleteCitizen(w.id)
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