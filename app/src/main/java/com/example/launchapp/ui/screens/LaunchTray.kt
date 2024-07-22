package com.example.launchapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.launchapp.R
import com.example.launchapp.ui.theme.buttonTextWhiteColor

@Composable
fun LaunchScreen(modifier: Modifier, startOrder: () -> Unit) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = startOrder, modifier = Modifier
                .widthIn(min = 250.dp)
                .padding(
                    horizontal = dimensionResource(R.dimen.padding_small)
                )
        ) {
            Text(
                text = stringResource(R.string.start_order),
                modifier = Modifier,
                style = buttonTextWhiteColor
            )
        }
    }
}

