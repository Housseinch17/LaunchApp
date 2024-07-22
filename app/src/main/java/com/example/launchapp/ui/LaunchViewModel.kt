package com.example.launchapp.ui

import androidx.lifecycle.ViewModel
import com.example.launchapp.data.Item
import com.example.launchapp.data.ItemUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import com.example.launchapp.data.Item.EntreeItem
import com.example.launchapp.data.Item.SideDishItem
import com.example.launchapp.data.Item.AccompanimentItem


@HiltViewModel
class LaunchViewModel @Inject constructor() : ViewModel() {
    private val _itemUiState = MutableStateFlow(ItemUiState())
    val itemUiState: StateFlow<ItemUiState> = _itemUiState.asStateFlow()

     fun updateEntree(entreeItem: EntreeItem) {
        val oldEntreeItem = _itemUiState.value.entree
        updateItem(oldEntreeItem,entreeItem)
    }

     fun updateSideDish(dishItem: SideDishItem) {
        val oldSideDishItem = _itemUiState.value.sideDish
        updateItem(oldSideDishItem,dishItem)
    }

     fun updateAccompaniment(accompanimentItem: AccompanimentItem) {
        val oldAccompanimentItem = _itemUiState.value.accompaniment
        updateItem(oldAccompanimentItem,accompanimentItem)
    }

    fun resetState() {
        _itemUiState.value = ItemUiState()
    }

    private fun updateItem(oldItem: Item?,newItem: Item){
       _itemUiState.update {
           val oldPrice = oldItem?.price ?: 0.0
           val newPrice = newItem.price
           val subTotal = it.subTotal - oldPrice + newPrice
           val tax = subTotal * 0.08
           val total = subTotal + tax
           it.copy(
               subTotal = subTotal,
               tax = tax,
               total = total,
               entree = if(newItem is EntreeItem) newItem else it.entree,
               sideDish = if(newItem is SideDishItem) newItem else it.sideDish,
               accompaniment = if(newItem is AccompanimentItem) newItem else it.accompaniment
           )
       }
    }
    }

