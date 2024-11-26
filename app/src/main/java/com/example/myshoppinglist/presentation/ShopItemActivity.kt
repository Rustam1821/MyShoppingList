package com.example.myshoppinglist.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myshoppinglist.R
import com.example.myshoppinglist.domain.ShopItem

class ShopItemActivity : AppCompatActivity() {

    private var screenMode = MODE_UNKNOWN
    private var shopItemId = ShopItem.UNDEFINED_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_item)
        parseIntent()
        launchRightMode()
    }

    private fun launchRightMode() {
        val fragment = when (screenMode) {
            MODE_EDIT -> ShopItemFragment.newInstanceEditItem(shopItemId)
            MODE_ADD -> ShopItemFragment.newInstanceAddItem()
            else -> throw RuntimeException("Unknown screen mode $screenMode")
        }
        supportFragmentManager.beginTransaction()
            .add(R.id.shop_item_container, fragment)
            .commit()
    }

    //    private fun launchEditMode() {
//        viewModel.getShopItem(shopItemId)
//        viewModel.shopItem.observe(this) {
//            etName.setText(it.name)
//            etCount.setText(it.count.toString())
//        }
//        buttonSave.setOnClickListener {
//            val name = etName.text.toString()
//            val count = etCount.text.toString()
//            viewModel.editShopItem(name, count)
//        }
//    }
//
//    private fun launchAddMode() {
//        buttonSave.setOnClickListener {
//            val name = etName.text.toString()
//            val count = etCount.text.toString()
//            viewModel.addShopItem(name, count)
//        }
//    }
//
//    private fun initVmObservers() {
//        viewModel.errorInputName.observe(this) { invalid ->
//            tilName.error = if (invalid) getString(R.string.error_input_name) else null
//        }
//        viewModel.errorInputCount.observe(this) { invalid ->
//            tilCount.error = if (invalid) getString(R.string.error_input_count) else null
//        }
//        viewModel.shouldCloseScreen.observe(this) {
//            finish()
//        }
//    }
//
//    private fun initViewsListeners() {
//        etName.doOnTextChanged { _, _, _, _ ->
//            viewModel.resetErrorInputName()
//        }
//        etCount.doOnTextChanged { _, _, _, _ ->
//            viewModel.resetErrorInputCount()
//        }
//        buttonSave.setOnClickListener {
//            val name = etName.text.toString()
//            val count = etCount.text.toString()
//            when (screenMode) {
//                MODE_EDIT -> viewModel.editShopItem(name, count)
//                MODE_ADD -> viewModel.addShopItem(name, count)
//            }
//        }
//    }
//
    private fun parseIntent() {
        if (!intent.hasExtra(EXTRA_MODE)) {
            throw RuntimeException("Param screen mode is absent")
        }
        val mode = intent.getStringExtra(EXTRA_MODE)
        if (mode != MODE_EDIT && mode != MODE_ADD) {
            throw RuntimeException("Unknown screen mode $mode")
        }
        screenMode = mode

        if (mode == MODE_EDIT) {
            if (!intent.hasExtra(EXTRA_SHOP_ITEM_ID)) {
                throw RuntimeException("Param shop item id is absent")
            }
            shopItemId = intent.getIntExtra(EXTRA_SHOP_ITEM_ID, ShopItem.UNDEFINED_ID)
        }
    }
//
//    private fun initViews() {
//        tilName = findViewById(R.id.til_name)
//        tilCount = findViewById(R.id.til_count)
//        etName = findViewById(R.id.et_name)
//        etCount = findViewById(R.id.et_count)
//        buttonSave = findViewById(R.id.save_button)
//    }

    companion object {
        private const val EXTRA_MODE = "extra_mode"
        private const val EXTRA_SHOP_ITEM_ID = "shop_item_id"
        private const val MODE_ADD = "mode_add"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_UNKNOWN = ""

        fun newIntentAddItem(context: Context): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_MODE, MODE_ADD)
            return intent
        }

        fun newIntentEditItem(context: Context, itemId: Int): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_MODE, MODE_EDIT)
            intent.putExtra(EXTRA_SHOP_ITEM_ID, itemId)
            return intent
        }
    }
}