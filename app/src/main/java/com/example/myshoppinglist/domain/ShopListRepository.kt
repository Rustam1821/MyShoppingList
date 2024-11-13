package com.example.myshoppinglist.domain

interface ShopListRepository {

    fun addShopItem(item: ShopItem)
    fun deleteShopItem(item: ShopItem)
    fun editShopItem(item: ShopItem)
    fun getShopItem(itemId: Int): ShopItem
    fun getShopList(): List<ShopItem>
}