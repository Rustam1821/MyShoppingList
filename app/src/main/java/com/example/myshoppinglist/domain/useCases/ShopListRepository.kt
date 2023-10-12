package com.example.myshoppinglist.domain.useCases

import androidx.lifecycle.LiveData
import com.example.myshoppinglist.domain.ShopItem

interface ShopListRepository {
    suspend fun addShopItem(item: ShopItem)
    suspend fun deleteShopItem(item: ShopItem)
    suspend fun editShopItem(item: ShopItem)
    fun getShopList(): LiveData<List<ShopItem>>
    suspend fun getShopItem(id: Int): ShopItem
}
