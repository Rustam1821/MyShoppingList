package com.example.myshoppinglist.domain.useCases

import com.example.myshoppinglist.domain.ShopItem

class GetShopListUseCase(
    private val shopListRepository: ShopListRepository,
) {

    fun getShopList(): List<ShopItem> {
        return shopListRepository.getShopList()
    }
}
