package com.example.launchapp.ui

import androidx.lifecycle.ViewModel
import com.example.launchapp.data.Item
import com.example.launchapp.data.ItemUiState
import com.example.launchapp.utils.format
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LaunchViewModel @Inject constructor() : ViewModel() {
    private val _itemUiState = MutableStateFlow(ItemUiState())
    val itemUiState: StateFlow<ItemUiState> = _itemUiState.asStateFlow()

    private fun updateEntree(price: Double, title: String) {
        _itemUiState.update {
            it.copy(entree = it.entree.copy(price = price, title = title))
        }
    }

    private fun updateSideDish(price: Double, title: String) {
        _itemUiState.update {
            it.copy(sideDish = it.sideDish.copy(price = price, title = title))
        }
    }

    private fun updateAccompaniment(price: Double, title: String) {
        _itemUiState.update {
            it.copy(accompaniment = it.accompaniment.copy(price = price, title = title))
        }
    }

    private fun updateSubTotal(price: Double, oldPrice: Double = 0.0) {
        _itemUiState.update {
            val newSubTotal = (it.subTotal + price - oldPrice).format(2)
            val newTax = (newSubTotal * 8 / 100).format(2)
            val newTotal = (newSubTotal + newTax).format(2)
            it.copy(
                subTotal = newSubTotal, tax = newTax, total = newTotal
            )
        }
    }

    fun resetState() {
        _itemUiState.value = ItemUiState()
    }

    private fun handleItemClick(
        item: Item, newTitle: String, price: Double, updateFunction: (Double, String) -> Unit
    ) {
        if (item.title.isEmpty()) {
            updateFunction(price, newTitle)
            updateSubTotal(price)
        } else {
            if (item.title == newTitle) {
                updateFunction(0.00, "")
                updateSubTotal(-price)
            } else {
                updateFunction(price, newTitle)
                updateSubTotal(price, oldPrice = item.price)
            }
        }
    }

    fun handleEvents(itemEvents: ItemEvents) {
        when (itemEvents) {
            is ItemEvents.EntreeClick -> {
                handleItemClick(
                    item = _itemUiState.value.entree,
                    newTitle = itemEvents.title,
                    price = itemEvents.price,
                    updateFunction = ::updateEntree
                )
            }

            is ItemEvents.SideDishClick -> {
                handleItemClick(
                    _itemUiState.value.sideDish,
                    itemEvents.title,
                    itemEvents.price,
                    ::updateSideDish
                )
            }

            is ItemEvents.AccompanimentClick -> {
                handleItemClick(
                    _itemUiState.value.accompaniment,
                    itemEvents.title,
                    itemEvents.price,
                    ::updateAccompaniment
                )
            }
        }
    }
}

sealed class ItemEvents {
    data class EntreeClick(val title: String, val price: Double) : ItemEvents()
    data class SideDishClick(val title: String, val price: Double) : ItemEvents()
    data class AccompanimentClick(val title: String, val price: Double) : ItemEvents()
}

