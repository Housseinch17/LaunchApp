package com.example.launchapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.launchapp.data.DataSource
import com.example.launchapp.ui.LaunchViewModel
import com.example.launchapp.ui.screens.AccompanimentList
import com.example.launchapp.ui.screens.EntreeList
import com.example.launchapp.ui.screens.LaunchScreen
import com.example.launchapp.ui.screens.OrderCheckOut
import com.example.launchapp.ui.screens.SideDishList

@Composable
fun Navigation(
    modifier: Modifier,
    navHostController: NavHostController,
    launchViewModel: LaunchViewModel
) {
    val itemUiState by launchViewModel.itemUiState.collectAsStateWithLifecycle()

    NavHost(
        navController = navHostController,
        startDestination = DataSource.LaunchScreen.LaunchTray.name
    ) {
        composable(DataSource.LaunchScreen.LaunchTray.name) {
            LaunchScreen(modifier = modifier, startOrder = {
                navHostController.navigate(DataSource.LaunchScreen.ChooseEntree.name)
            })
        }
        composable(DataSource.LaunchScreen.ChooseEntree.name) {
            EntreeList(
                modifier = modifier,
                list = DataSource.chooseEntree,
                isTitle = itemUiState.entree.title,
                onButtonClick = { entree->
                    launchViewModel.updateEntree(entree)
                },
                onCancel = {
                    onCancelOrder(launchViewModel, navHostController)
                },
                onNext = { navHostController.navigate(DataSource.LaunchScreen.ChooseSideDish.name) }
            )
        }
        composable(DataSource.LaunchScreen.ChooseSideDish.name) {
            SideDishList(
                modifier = modifier,
                list = DataSource.chooseSideDish,
                isTitle = itemUiState.sideDish.title,
                onButtonClick = { sideDish->
                    launchViewModel.updateSideDish(sideDish)
                },
                onCancel = { onCancelOrder(launchViewModel, navHostController) },
                onNext = { navHostController.navigate(DataSource.LaunchScreen.ChooseAccompaniment.name) })
        }
        composable(DataSource.LaunchScreen.ChooseAccompaniment.name) {
            AccompanimentList(
                modifier = modifier,
                list = DataSource.chooseAccompaniment,
                isTitle = itemUiState.accompaniment.title,
                onButtonClick = { accompanimentItem->
                    launchViewModel.updateAccompaniment(accompanimentItem)
                },
                onCancel = { onCancelOrder(launchViewModel, navHostController) },
                onNext = { navHostController.navigate(DataSource.LaunchScreen.OrderCheckOut.name) })
        }
        composable(DataSource.LaunchScreen.OrderCheckOut.name) {
            OrderCheckOut(
                modifier = modifier,
                itemUiState,
                onCancel = {
                    onCancelOrder(
                        navHostController = navHostController,
                        orderViewModel = launchViewModel
                    )
                },
                onSubmit = {}
            )
        }
    }
}

private fun onCancelOrder(
    orderViewModel: LaunchViewModel,
    navHostController: NavHostController,
) {
    orderViewModel.resetState()
    navHostController.popBackStack(DataSource.LaunchScreen.LaunchTray.name, inclusive = false)
}