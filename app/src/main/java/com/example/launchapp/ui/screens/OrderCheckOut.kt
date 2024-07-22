package com.example.launchapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.launchapp.R
import com.example.launchapp.data.Item
import com.example.launchapp.data.ItemUiState
import com.example.launchapp.ui.sharedComponents.SharedButtons
import com.example.launchapp.ui.theme.itemDescription
import com.example.launchapp.ui.theme.itemPrice
import com.example.launchapp.ui.theme.itemTitle

@Composable
fun OrderCheckOut(
    modifier: Modifier,
    itemUiState: ItemUiState,
    onCancel: ()-> Unit,
    onSubmit: ()-> Unit,
) {
    CheckOut(
        modifier, itemUiState.entree, itemUiState.sideDish, itemUiState.accompaniment, itemUiState.subTotal, itemUiState.tax, itemUiState.total,onCancel,onSubmit
    )
}

@Composable
fun CheckOut(
    modifier: Modifier,
    entree: Item.EntreeItem?,
    sideDish: Item.SideDishItem?,
    accompaniment: Item.AccompanimentItem?,
    subTotal: Double,
    tax: Double,
    total: Double,
    onCancel: ()-> Unit,
    onSubmit: ()-> Unit,
) {
    Column(
        modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
    ) {
        Text(
            text = stringResource(id = R.string.order_summary),
            modifier = Modifier.fillMaxWidth(),
            style = itemTitle
        )
        OrderSummary(Modifier.fillMaxWidth(), entree?.title, entree?.price)
        OrderSummary(Modifier.fillMaxWidth(), sideDish?.title, sideDish?.price)
        OrderSummary(Modifier.fillMaxWidth(), accompaniment?.title, accompaniment?.price)
        HorizontalDivider(Modifier.fillMaxWidth(), thickness = 1.dp, color = Color.Gray)
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.End,verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))) {
            TotalSummary(
                Modifier.fillMaxWidth(),
                title = stringResource(id = R.string.subtotal),
                total = subTotal,
                style = itemDescription
            )
            TotalSummary(
                Modifier.fillMaxWidth(),
                title = stringResource(id = R.string.tax),
                total = tax,
                itemDescription
            )
            TotalSummary(
                Modifier.fillMaxWidth(),
                title = stringResource(id = R.string.total),
                total = total,
                itemTitle
            )
        }
        SharedButtons(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = dimensionResource(id = R.dimen.padding_small)),
            isTitleEmpty = "", isNext = false, onCancel = onCancel, onNextOrOnSubmit = onSubmit)
    }
}

@Composable
fun OrderSummary(modifier: Modifier, title: String?, price: Double?) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = ""+title, style = itemDescription)
        Text(text = "$$price", style = itemPrice)
    }
}

@Composable
fun TotalSummary(modifier: Modifier = Modifier, title: String, total: Double, style: TextStyle) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.End) {
        Text(text = title, style = style)
        Text(text = "$$total", style = style)
    }
}