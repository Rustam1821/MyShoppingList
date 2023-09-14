package com.example.myshoppinglist.domain.useCases

import com.example.myshoppinglist.domain.ShopItem

class AddShopItemUseCase(
    private val shopListRepository: ShopListRepository,
) {

    fun addShopItem(item: ShopItem) {
        shopListRepository.addShopItem(item)
    }
}
