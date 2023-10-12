package com.example.myshoppinglist.domain.useCases

import com.example.myshoppinglist.domain.ShopItem

class DeleteShopItemUseCase(
    private val shopListRepository: ShopListRepository,
) {

    suspend fun deleteShopItem(item: ShopItem) {
        shopListRepository.deleteShopItem(item)
    }
}
