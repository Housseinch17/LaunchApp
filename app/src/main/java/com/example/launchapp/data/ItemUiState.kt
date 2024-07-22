package com.example.launchapp.data

data class ItemUiState(
    val entree: Item = Item("", "", 0.0),
    val sideDish: Item = Item("", "", 0.0),
    val accompaniment: Item = Item("", "", 0.0),
    val subTotal: Double = 0.0,
    val tax: Double = 0.0,
    val total: Double = 0.0,
)