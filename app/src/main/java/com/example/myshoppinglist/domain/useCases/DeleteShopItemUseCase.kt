package com.example.myshoppinglist.domain.useCases

import com.example.myshoppinglist.domain.ShopItem

class DeleteShopItemUseCase(
    private val shopListRepository: ShopListRepository,
) {

    fun deleteShopItem(item: ShopItem) {
        shopListRepository.deleteShopItem(item)
    }
}
