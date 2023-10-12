package com.example.myshoppinglist.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.myshoppinglist.data.ShopListRepositoryImpl
import com.example.myshoppinglist.domain.ShopItem
import com.example.myshoppinglist.domain.useCases.DeleteShopItemUseCase
import com.example.myshoppinglist.domain.useCases.EditShopItemUseCase
import com.example.myshoppinglist.domain.useCases.GetShopListUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ShopListRepositoryImpl(application)

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    val shopList = getShopListUseCase.getShopList()

    fun deleteShopItem(item: ShopItem) {
        coroutineScope.launch {
            deleteShopItemUseCase.deleteShopItem(item)
        }
    }

    fun changeEnableState(item: ShopItem) {
        coroutineScope.launch {
            val updatedItem = item.copy(enabled = !item.enabled)
            editShopItemUseCase.editShopItem(updatedItem)
        }
    }

    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }
}