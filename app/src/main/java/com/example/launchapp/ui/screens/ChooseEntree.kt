package com.example.launchapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.launchapp.data.Item
import com.example.launchapp.ui.sharedComponents.ItemList
import com.example.launchapp.data.Item.EntreeItem

@Composable
fun EntreeList(
    modifier: Modifier, list: List<EntreeItem>, isTitle: String, onButtonClick: (EntreeItem) ->Unit,
    onCancel: () ->Unit,
    onNext: () ->Unit){
    ItemList(
        modifier = modifier,
        list = list,
        isTitle = isTitle,
        onButtonClick = onButtonClick as (Item) -> Unit,
        onCancel = onCancel,
        onNext = onNext)
}

