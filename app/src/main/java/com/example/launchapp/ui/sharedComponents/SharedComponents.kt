package com.example.launchapp.ui.sharedComponents

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.RadioButton
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
import com.example.launchapp.ui.theme.Purple
import com.example.launchapp.ui.theme.buttonTextPurpleColor
import com.example.launchapp.ui.theme.buttonTextWhiteColor
import com.example.launchapp.ui.theme.itemDescription
import com.example.launchapp.ui.theme.itemPrice
import com.example.launchapp.ui.theme.itemTitle


@Composable
fun ItemList(
    modifier: Modifier,
    list: List<Item>,
    isTitle: String,
    onButtonClick: (price: Double, title: String) -> Unit,
    onCancel: () -> Unit,
    onNext: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
    ) {
        list.forEach { item ->
            ItemComponent(
                modifier = Modifier.fillMaxWidth(),
                title = item.title,
                description = item.description,
                price = item.price,
                isTitle = isTitle
            ) {
                onButtonClick(item.price, item.title)
            }
        }
        SharedButtons(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(vertical = dimensionResource(id = R.dimen.shape_medium)),
            isTitleEmpty = isTitle,
            isNext = true,
            onCancel = onCancel,
            onNextOrOnSubmit = onNext
        )
    }
}

@Composable
fun ItemComponent(
    modifier: Modifier,
    title: String,
    description: String,
    price: Double,
    isTitle: String,
    onButtonClick: () -> Unit
) {
    val isSelected = isTitle == title
    Row(modifier = modifier) {
        RadioButton(
            selected = isSelected, onClick = onButtonClick,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .size(30.dp),
        )
        ClassComponent(
            modifier = Modifier.fillMaxWidth(1f),
            title = title,
            description = description,
            price = price
        )
    }
}

@Composable
fun ClassComponent(
    modifier: Modifier,
    title: String,
    description: String,
    price: Double
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_xsmall)),
    ) {
        Text(text = title, modifier = modifier, style = itemTitle)
        Text(
            text = description,
            modifier = modifier,
            style = itemDescription
        )
        Text(text = "$$price", modifier = modifier, style = itemPrice)
        HorizontalDivider(modifier = modifier, thickness = 1.dp, color = Color.LightGray)
    }
}

@Composable
fun SharedButtons(
    modifier: Modifier,
    isTitleEmpty: String,
    isNext: Boolean,
    onCancel: () -> Unit,
    onNextOrOnSubmit: () -> Unit,
) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceBetween) {
        CancelButtonComponent(
            buttonStyle = buttonTextPurpleColor,
            text = stringResource(R.string.cancel),
            borderStroke = BorderStroke(1.dp, Color.Black),
            buttonColor = ButtonDefaults.buttonColors(Color.White),
            onButtonClick = onCancel
        )
        if (isNext) {
            NextButtonComponent(
                buttonStyle = buttonTextWhiteColor,
                text = stringResource(R.string.next),
                borderStroke = null,
                isTitleEmpty = isTitleEmpty,
                buttonColor = ButtonDefaults.buttonColors(
                    Purple
                ),
                onButtonClick = onNextOrOnSubmit
            )
        } else {
            CancelButtonComponent(
                buttonStyle = buttonTextWhiteColor,
                text = stringResource(R.string.submit),
                borderStroke = null,
                buttonColor = ButtonDefaults.buttonColors(
                    Purple
                ),
                onButtonClick = onNextOrOnSubmit
            )
        }
    }
}

@Composable
fun CancelButtonComponent(
    buttonStyle: TextStyle,
    text: String,
    borderStroke: BorderStroke?,
    buttonColor: ButtonColors,
    onButtonClick: () -> Unit
) {
    Button(
        modifier = Modifier.widthIn(min = 140.dp),
        onClick = onButtonClick,
        shape = RoundedCornerShape(dimensionResource(R.dimen.shape_medium)),
        border = borderStroke,
        colors = buttonColor,
    ) {
        Text(text = text, style = buttonStyle)
    }
}

@Composable
fun NextButtonComponent(
    buttonStyle: TextStyle,
    text: String,
    borderStroke: BorderStroke?,
    isTitleEmpty: String = "",
    buttonColor: ButtonColors,
    onButtonClick: () -> Unit
) {
    val isEnabled = isTitleEmpty.isNotEmpty()
    Button(
        modifier = Modifier.widthIn(min = 140.dp),
        onClick = onButtonClick,
        shape = RoundedCornerShape(dimensionResource(R.dimen.shape_medium)),
        border = borderStroke,
        colors = buttonColor,
        enabled = isEnabled
    ) {
        Text(text = text, style = buttonStyle)
    }
}