package com.example.myshoppinglist.domain.useCases

import androidx.lifecycle.LiveData
import com.example.myshoppinglist.domain.ShopItem

class GetShopListUseCase(
    private val shopListRepository: ShopListRepository,
) {

    fun getShopList(): LiveData<List<ShopItem>> {
        return shopListRepository.getShopList()
    }
}
