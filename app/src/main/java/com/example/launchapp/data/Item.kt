package com.example.launchapp.data


sealed class Item(open val title: String = "", open val description: String = "", open val price: Double = 0.0) {
    data class EntreeItem(override val title: String,override val description: String,override val price: Double) : Item(title,description,price)
    data class SideDishItem(override val title: String,override val description: String,override val price: Double) : Item(title,description,price)
    data class AccompanimentItem(override val title: String,override val description: String,override val price: Double) : Item(title,description,price)
}
