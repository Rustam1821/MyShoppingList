package com.example.myshoppinglist.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.myshoppinglist.R
import com.example.myshoppinglist.domain.ShopItem

class ShopListAdapter : RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {
    var shopList = listOf<ShopItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_shop_enabled,
            parent,
            false
        )
        return ShopItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return shopList.size
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val item = shopList[position]
        val status = if (item.enabled) {
            "Active"
        } else {
            "Inactive"
        }
        holder.itemView.setOnLongClickListener {
            true
        }
        if (item.enabled) {
            holder.tvName.text = "${item.name} - $status"
            holder.tvCount.text = item.count.toString()
            holder.tvName.setTextColor(ContextCompat.getColor(holder.view.context, android.R.color.holo_red_dark))
        }
    }

    override fun onViewRecycled(viewHolder: ShopItemViewHolder) {
        super.onViewRecycled(viewHolder)
        viewHolder.tvName.text = null
        viewHolder.tvCount.text = null
        viewHolder.tvName.setTextColor(ContextCompat.getColor(viewHolder.view.context, android.R.color.white))
    }

    class ShopItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.tv_name)
        val tvCount = view.findViewById<TextView>(R.id.tv_count)
    }
}