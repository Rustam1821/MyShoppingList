package com.example.myshoppinglist.data

import com.example.myshoppinglist.domain.ShopItem

class ShopListMapper {

    fun mapEntityToDbModel(shopItem: ShopItem): ShopItemDbModel =
        ShopItemDbModel(
            shopItem.id,
            shopItem.name,
            shopItem.count,
            shopItem.enabled
        )

    fun mapDbModelToEntity(dbModel: ShopItemDbModel): ShopItem =
        ShopItem(
            dbModel.name,
            dbModel.count,
            dbModel.enabled,
            dbModel.id,
        )

    fun mapListDbModelToListEntity(list: List<ShopItemDbModel>): List<ShopItem> {
        return list.map(::mapDbModelToEntity)
    }
}