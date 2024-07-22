package com.example.launchapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.launchapp.data.DataSource
import com.example.launchapp.ui.LaunchViewModel
import com.example.launchapp.ui.navigation.Navigation
import com.example.launchapp.ui.theme.itemTitle

@Composable
fun LaunchApp() {
    val launchViewModel: LaunchViewModel = viewModel()
    val navHostController = rememberNavController()
// Get current back stack entry
    val backStackEntry by navHostController.currentBackStackEntryAsState()
// Get the name of the current screen
    val currentScreen = DataSource.LaunchScreen.valueOf(
        backStackEntry?.destination?.route ?: DataSource.LaunchScreen.LaunchTray.name
    )
    Scaffold(
        topBar = {
            AppBar(
                modifier = Modifier.fillMaxWidth(),
                launchScreen = currentScreen,
                canNavigateBack = navHostController.previousBackStackEntry != null
            ) {
                navHostController.navigateUp()
            }
        }
    ) { innerPadding ->
        Navigation(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(
                    vertical = dimensionResource(R.dimen.padding_xsmall),
                    horizontal = dimensionResource(R.dimen.padding_medium)
                ),
            navHostController = navHostController,
            launchViewModel = launchViewModel
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    modifier: Modifier, launchScreen: DataSource.LaunchScreen, canNavigateBack: Boolean,
    navigateBack: () -> Unit
) {
    TopAppBar(title = {
        Text(
            text = stringResource(id = launchScreen.title),
            modifier = modifier.fillMaxWidth(1f),
            textAlign = TextAlign.Center,
            style = itemTitle.copy(fontSize = 24.sp),
        )
    },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(
                    onClick = navigateBack, modifier = Modifier.size(30.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(
                            id = R.string.back
                        )
                    )
                }
            }
        })
}
