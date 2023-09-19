package com.example.myshoppinglist.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myshoppinglist.R
import com.example.myshoppinglist.domain.ShopItem

class ShopListAdapter : RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {

    var shopList = listOf<ShopItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ShopItemViewHolder(val view: View) : ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tv_name)
        val tvCount: TextView = view.findViewById(R.id.tv_count)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val resource = if (viewType == ItemType.ACTIVE.type) R.layout.item_shop_enabled else R.layout.item_shop_disabled
        val view = LayoutInflater.from(parent.context).inflate(
            resource,
            parent,
            false
        )
        return ShopItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return shopList.size
    }

    override fun onBindViewHolder(viewHolder: ShopItemViewHolder, position: Int) {
        val shopItem = shopList[position]
        val status = if (shopItem.enabled) "Active" else "Passive"
        viewHolder.tvName.text = "${shopItem.name} is $status"
        viewHolder.tvCount.text = shopItem.count.toString()
        viewHolder.view.setOnLongClickListener {
            Log.d("--->", "item # $position was clicked")
            true
        }
        if (shopItem.enabled) {
            viewHolder.tvName.setTextColor(
                ContextCompat.getColor(
                    viewHolder.view.context,
                    android.R.color.holo_red_light
                )
            )
        } else {
            viewHolder.tvName.setTextColor(ContextCompat.getColor(viewHolder.view.context, android.R.color.white))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (shopList[position].enabled) ItemType.ACTIVE.type else ItemType.PASSIVE.type
    }
}

enum class ItemType(val type: Int) {
    ACTIVE(1),
    PASSIVE(2)
}