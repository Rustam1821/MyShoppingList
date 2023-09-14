package com.example.myshoppinglist.domain.useCases

import com.example.myshoppinglist.domain.ShopItem

interface ShopListRepository {
    fun addShopItem(item: ShopItem)
    fun deleteShopItem(item: ShopItem)
    fun editShopItem(item: ShopItem)
    fun getShopList(): List<ShopItem>
    fun getShopItem(id: Int): ShopItem
}
