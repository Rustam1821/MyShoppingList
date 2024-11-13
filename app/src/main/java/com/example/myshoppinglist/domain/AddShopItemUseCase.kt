package com.example.myshoppinglist.domain

class AddShopItemUseCase(private val shopListRepository: ShopListRepository) {

    fun addShopItem(item: ShopItem) {
        shopListRepository.addShopItem(item)
    }

}