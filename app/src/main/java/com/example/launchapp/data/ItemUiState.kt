package com.example.launchapp.data
import com.example.launchapp.data.Item.EntreeItem
import com.example.launchapp.data.Item.SideDishItem
import com.example.launchapp.data.Item.AccompanimentItem

data class ItemUiState(
    val entree: EntreeItem = EntreeItem("","",0.0),
    val sideDish: SideDishItem = SideDishItem("","",0.0),
    val accompaniment: AccompanimentItem = AccompanimentItem("","",0.0),
    val subTotal: Double = 0.0,
    val tax: Double =0.0,
    val total: Double = 0.0,
)