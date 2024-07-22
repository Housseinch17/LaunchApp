package com.example.launchapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.launchapp.data.Item
import com.example.launchapp.ui.sharedComponents.ItemList

@Composable
fun SideDishList(modifier: Modifier, list: List<Item>, isTitle: String, onButtonClick: (price: Double,title: String) ->Unit, onCancel: () ->Unit, onNext: () ->Unit){
    ItemList(
        modifier = modifier,
        list = list,
        isTitle = isTitle,
        onButtonClick = { price,title->
            onButtonClick(price,title)
        },
        onCancel = onCancel,
        onNext = onNext)
}