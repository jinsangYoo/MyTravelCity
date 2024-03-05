package com.jinsang.mytravelcity.ui

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jinsang.mytravelcity.ui.utils.TravelCityContentType
import com.jinsang.mytravelcity.ui.utils.TravelCityNavigationType

@Composable
fun MyTravelCityApp(
    windowSize: WindowWidthSizeClass,
    onBackPressed: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val navigationType: TravelCityNavigationType
    val contentType: TravelCityContentType

    when (windowSize) {
        WindowWidthSizeClass.Compact -> {
            navigationType = TravelCityNavigationType.BOTTOM_NAVIGATION
            contentType = TravelCityContentType.ListOnly
        }
        WindowWidthSizeClass.Medium -> {
            navigationType = TravelCityNavigationType.NAVIGATION_RAIL
            contentType = TravelCityContentType.ListOnly
        }
        WindowWidthSizeClass.Expanded -> {
            navigationType = TravelCityNavigationType.PERMANENT_NAVIGATION_DRAWER
            contentType = TravelCityContentType.ListAndDetail
        }
        else -> {
            navigationType = TravelCityNavigationType.BOTTOM_NAVIGATION
            contentType = TravelCityContentType.ListOnly
        }
    }

    val viewModel: TravelCityViewModel = viewModel()
    val travelCityUiState = viewModel.uiState.collectAsState().value

    TravelCityMainScreen(
        navigationType = navigationType,
        contentType = contentType,
        travelCityUiState = travelCityUiState,
        onTabPressed = {
            viewModel.updateCurrentStoreCategory(it)
            viewModel.resetHomeScreenStates()
        },
        onStorePressed = {
            viewModel.updateDetailsScreenStates(it)
        },
        onDetailScreenBackPressed = {
            viewModel.resetHomeScreenStates()
        },
        modifier = modifier
    )
}