package com.example.myshoppinglist.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.myshoppinglist.R
import com.example.myshoppinglist.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), ShopItemFragment.OnEditingFinishedListener {

    private lateinit var viewModel: MainViewModel
    private lateinit var shopListAdapter: ShopListAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopList.observe(this) {
            shopListAdapter.submitList(it)
        }

        binding.buttonAddShopItem.setOnClickListener {
            if (isOnePaneMode()) {
                val intent = ShopItemActivity.newIntentAddItem(this)
                startActivity(intent)
            } else {
                val fragment = ShopItemFragment.newInstanceAddItem()
                launchFragment(fragment)
            }
        }
    }

    private fun setupRecyclerView() {
        shopListAdapter = ShopListAdapter()
        with(binding.rvShopList) {
            adapter = shopListAdapter
            recycledViewPool.setMaxRecycledViews(ShopListAdapter.VIEW_TYPE_ENABLED, ShopListAdapter.MAX_POOL_SIZE)
            recycledViewPool.setMaxRecycledViews(ShopListAdapter.VIEW_TYPE_DISABLED, ShopListAdapter.MAX_POOL_SIZE)
        }
        setupClicksListener()
        setupSwipeListener(binding.rvShopList)
    }

    private fun setupClicksListener() {
        shopListAdapter.onShopItemLongClick = {
            viewModel.changeEnableState(it)
        }
        shopListAdapter.onShopItemClick = {
            if (isOnePaneMode()) {
                val intent = ShopItemActivity.newIntentEditItem(this, it.id)
                startActivity(intent)
            } else {
                val fragment = ShopItemFragment.newInstanceEditItem(it.id)
                launchFragment(fragment)
            }
        }
    }

    private fun launchFragment(fragment: ShopItemFragment) {
        supportFragmentManager.apply {
            popBackStack()
            beginTransaction()
                .replace(R.id.shop_item_container, fragment)
                .addToBackStack(null)
                .commit()
        }
    }

    private fun isOnePaneMode(): Boolean {
        return binding.shopItemContainer == null
    }

    private fun setupSwipeListener(recyclerView: RecyclerView) {
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val shopItem = shopListAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteShopItem(shopItem)
            }

        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    override fun onEditingFinished() {
        Toast.makeText(this, "Success", Toast.LENGTH_LONG).show()
        onBackPressedDispatcher.onBackPressed()
    }

}