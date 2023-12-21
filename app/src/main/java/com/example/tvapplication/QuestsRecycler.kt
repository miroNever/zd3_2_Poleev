package com.example.tvapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class QuestsRecycler(val context: Context, val questsList:ArrayList<Quests>):RecyclerView.Adapter<QuestsRecycler.QuestsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.quests_adapter, parent, false)
        return QuestsViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuestsViewHolder, position: Int) {
        val quest = questsList[position]

        holder.imageQuests.setImageResource(quest.image)
        holder.titleQuest.text = quest.title
        holder.textQuest.text = quest.text

    }

    override fun getItemCount(): Int {
        return questsList.size
    }

    inner class QuestsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageQuests: ImageView = itemView.findViewById(R.id.image_quests)
        val titleQuest: TextView = itemView.findViewById(R.id.title_quest)
        val textQuest: TextView = itemView.findViewById(R.id.description)
        val btDetails: TextView = itemView.findViewById(R.id.bt_details)
    }

}