package com.alex.smallgoal.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alex.smallgoal.R
import com.alex.smallgoal.bean.TargetItem

class TargetAdapter(private val items: List<TargetItem>) :
  RecyclerView.Adapter<TargetAdapter.ViewHolder>() {

  inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val tvTitle: TextView = view.findViewById(R.id.tvTitle)
    private val tvTargetValue: TextView = view.findViewById(R.id.tvTargetValue)
    private val tvProgressValue: TextView = view.findViewById(R.id.tvProgressValue)
    private val tvCurrentCount: TextView = view.findViewById(R.id.tvCurrentCount)

    fun bind(item: TargetItem) {
      tvTitle.text = item.title
      tvTargetValue.text = item.target.toString()
      tvCurrentCount.text = "${item.curValue}"
      tvProgressValue.text = "${((item.curValue / item.target.toFloat()) * 100).toInt()}"
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context)
      .inflate(R.layout.item_target_card, parent, false)
    return ViewHolder(view)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(items[position])
  }

  override fun getItemCount() = items.size
}