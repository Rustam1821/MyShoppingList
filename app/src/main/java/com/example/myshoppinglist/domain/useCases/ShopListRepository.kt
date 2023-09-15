package com.example.myshoppinglist.domain.useCases

import androidx.lifecycle.LiveData
import com.example.myshoppinglist.domain.ShopItem

interface ShopListRepository {
    fun addShopItem(item: ShopItem)
    fun deleteShopItem(item: ShopItem)
    fun editShopItem(item: ShopItem)
    fun getShopList(): LiveData<List<ShopItem>>
    fun getShopItem(id: Int): ShopItem
}
