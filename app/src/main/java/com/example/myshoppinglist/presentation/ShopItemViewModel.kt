package com.example.myshoppinglist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myshoppinglist.data.ShopListRepositoryImpl
import com.example.myshoppinglist.domain.AddShopItemUseCase
import com.example.myshoppinglist.domain.EditShopItemUseCase
import com.example.myshoppinglist.domain.GetShopItemUseCase
import com.example.myshoppinglist.domain.ShopItem

class ShopItemViewModel : ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val getShopItemUseCase = GetShopItemUseCase(repository)
    private val addShopItemUseCase = AddShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() {
            return _errorInputName
        }

    private val _errorCount = MutableLiveData<Boolean>()
    private val errorCount: LiveData<Boolean>
        get() {
            return _errorCount
        }

    private val _shopItem = MutableLiveData<ShopItem>()
    val shopItem: LiveData<ShopItem>
        get() {
            return _shopItem
        }

    private val _shouldCloseScreen = MutableLiveData<Unit>()
    val shouldCloseScreen: LiveData<Unit>
        get() {
            return _shouldCloseScreen
        }

    fun getShopItem(itemId: Int) {
        _shopItem.value = getShopItemUseCase.getShopItem(itemId)
    }

    fun addShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name, count)
        if (fieldsValid) {
            val shopItem = ShopItem(name, count, true)
            addShopItemUseCase.addShopItem(shopItem)
        }
        finishWork()
    }

    fun editShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name, count)
        if (fieldsValid) {
            _shopItem.value?.let {
                val item = it.copy(name = name, count = count)
                editShopItemUseCase.editShopItem(item)
                finishWork()
            }
        }
    }

    private fun parseName(inputName: String?): String {
        return inputName?.trim() ?: ""
    }

    private fun parseCount(inputCount: String?): Int {
        return inputCount?.trim()?.toIntOrNull() ?: 0
    }

    private fun validateInput(name: String, count: Int): Boolean {
        var result = true
        if (name.isEmpty()) {
            _errorInputName.value = true
            result = false
        } else if (count <= 0) {
            //TODO: show error input cont
            result = false
        }
        return result
    }

    fun resetErrorInputName() {
        _errorInputName.value = false
    }

    fun resetErrorInputCount() {
        _errorCount.value = false
    }

    private fun finishWork() {
        _shouldCloseScreen.value = Unit
    }
}