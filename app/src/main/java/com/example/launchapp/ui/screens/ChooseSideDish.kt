package com.example.launchapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.launchapp.data.Item
import com.example.launchapp.data.Item.SideDishItem
import com.example.launchapp.ui.sharedComponents.ItemList

@Composable
fun SideDishList(modifier: Modifier, list: List<SideDishItem>, isTitle: String, onButtonClick: (SideDishItem) ->Unit, onCancel: () ->Unit, onNext: () ->Unit){
    ItemList(
        modifier = modifier,
        list = list,
        isTitle = isTitle,
        onButtonClick = onButtonClick as (Item) -> Unit,
        onCancel = onCancel,
        onNext = onNext)
}