package com.example.myshoppinglist.data

import com.example.myshoppinglist.domain.ShopItem
import com.example.myshoppinglist.domain.useCases.ShopListRepository

object ShopListRepositoryImpl : ShopListRepository {

    private val shopList = mutableListOf<ShopItem>()
    private var autoIncrementId = 0

    init {
        for (i in 0 until 10) {
            val item = ShopItem("Name $i", i, true)
            addShopItem(item)
        }
    }

    override fun addShopItem(item: ShopItem) {
        if (item.id == ShopItem.UNDEFINED_ID) {
            item.id = autoIncrementId++
        }
        shopList.add(item)
    }

    override fun deleteShopItem(item: ShopItem) {
        shopList.remove(item)
    }

    override fun editShopItem(item: ShopItem) {
        val oldItem = getShopItem(item.id)
        shopList.remove(oldItem)
        addShopItem(item)
    }

    override fun getShopList(): List<ShopItem> {
        return shopList.toList()
    }

    override fun getShopItem(id: Int): ShopItem {
        return shopList.find { it.id == id } ?: throw NullPointerException("Element with id $id not found")
    }
}