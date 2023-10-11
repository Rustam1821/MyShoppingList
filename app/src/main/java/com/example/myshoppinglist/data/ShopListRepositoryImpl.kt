package com.example.myshoppinglist.data

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.myshoppinglist.domain.ShopItem
import com.example.myshoppinglist.domain.useCases.ShopListRepository

class ShopListRepositoryImpl(
    private val application: Application
) : ShopListRepository {

    private val shopListDao = AppDatabase.getInstance(application).shopListDao()
    private val mapper = ShopListMapper()

    override fun addShopItem(item: ShopItem) {
        val shopItemDbModel = mapper.mapEntityToDbModel(item)
        shopListDao.addShopItem(shopItemDbModel)
    }

    override fun deleteShopItem(item: ShopItem) {
        shopListDao.deleteShopItem(item.id)
    }

    override fun editShopItem(item: ShopItem) {
        addShopItem(item)
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        val list = shopListDao.getShopList()
        return shopListLD
    }

    override fun getShopItem(id: Int): ShopItem {
        val dbModel = shopListDao.getShopItem(id)
        return mapper.mapDbModelToEntity(dbModel)
    }
}