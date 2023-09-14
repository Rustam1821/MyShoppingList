package com.example.myshoppinglist.domain.useCases

import com.example.myshoppinglist.domain.ShopItem

class GetShopItemUseCase(
    private val shopListRepository: ShopListRepository,
) {

    fun getShopItem(id: Int): ShopItem {
        return shopListRepository.getShopItem(id)
    }
}
