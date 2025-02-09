package com.example.launchapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.launchapp.data.Item
import com.example.launchapp.data.Item.AccompanimentItem
import com.example.launchapp.ui.sharedComponents.ItemList

@Composable
fun AccompanimentList(modifier: Modifier, list: List<AccompanimentItem>, isTitle: String, onButtonClick: (AccompanimentItem) ->Unit, onCancel: () ->Unit, onNext: () ->Unit){
    ItemList(
        modifier = modifier,
        list = list,
        isTitle = isTitle,
        onButtonClick = onButtonClick as (Item) -> Unit,
        onCancel = onCancel,
        onNext = onNext)
}