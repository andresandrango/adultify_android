package com.example.adultifyandroid.ui.mission

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.adultifyandroid.R
import com.example.adultifyandroid.gameserver.GameService
import com.example.adultifyandroid.gameserver.Mission
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

class MissionAdapter @AssistedInject constructor(
    private var gameService: GameService,
    @Assisted private var items: MutableList<Mission>) :
    RecyclerView.Adapter<MissionAdapter.MyViewHolder>() {

        inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            var nameView: TextView = view.findViewById(R.id.name)
            var stateView: TextView = view.findViewById(R.id.state)
            var ownerNameView: TextView = view.findViewById(R.id.owner)
            var energyView: TextView = view.findViewById(R.id.energy)
            var timeView: TextView = view.findViewById(R.id.time)
            var scheduledAtView: TextView = view.findViewById(R.id.scheduledAt)
            var dueAtView: TextView = view.findViewById(R.id.dueAt)
            var completedAtView: TextView = view.findViewById(R.id.completedAt)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.mission_row_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = items[position]
        holder.nameView.text = item.name
        holder.stateView.text = item.state
        holder.ownerNameView.text = item.owner?.name
        holder.energyView.text = item.reward.energy.toString()
        holder.timeView.text = item.reward.time.toString()
        item.scheduledAt.toString().also { holder.scheduledAtView.text = it }
        item.dueAt.toString().also { holder.dueAtView.text = it }
        item.completedAt.toString().also { holder.completedAtView.text = it }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}