package com.example.myshoppinglist.domain.useCases

import com.example.myshoppinglist.domain.ShopItem

class EditShopItemUseCase(
    private val shopListRepository: ShopListRepository,
) {

    suspend fun editShopItem(item: ShopItem) {
        shopListRepository.editShopItem(item)
    }
}
