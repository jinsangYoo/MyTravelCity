package com.jinsang.mytravelcity.ui

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.jinsang.mytravelcity.R
import com.jinsang.mytravelcity.data.Store
import com.jinsang.mytravelcity.data.StoreCategoryType
import com.jinsang.mytravelcity.ui.common.StoreListOnlyContent
import com.jinsang.mytravelcity.ui.common.TravelCityLogo
import com.jinsang.mytravelcity.ui.theme.MyTravelCityTheme
import com.jinsang.mytravelcity.ui.utils.TravelCityContentType
import com.jinsang.mytravelcity.ui.utils.TravelCityNavigationType

@Composable
fun TravelCityMainScreen(
    navigationType: TravelCityNavigationType,
    contentType: TravelCityContentType,
    trableCityUiState: TravelCityUiState,
    onTabPressed: (StoreCategoryType) -> Unit,
    onStorePressed: (Store) -> Unit,
    onDetailScreenBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    val navigationItemContentList = listOf(
        NavigationItemContent(
            storeCategoryType = StoreCategoryType.Food,
            icon = ImageVector.vectorResource(id = R.drawable.outline_fastfood_24),
            text = stringResource(id = R.string.tab_food)
        ),
        NavigationItemContent(
            storeCategoryType = StoreCategoryType.Park,
            icon = ImageVector.vectorResource(id = R.drawable.outline_park_24),
            text = stringResource(id = R.string.tab_park)
        ),
        NavigationItemContent(
            storeCategoryType = StoreCategoryType.Shopping,
            icon = ImageVector.vectorResource(id = R.drawable.outline_shopping_cart_24),
            text = stringResource(id = R.string.tab_shopping)
        ),
        NavigationItemContent(
            storeCategoryType = StoreCategoryType.ForChild,
            icon = ImageVector.vectorResource(id = R.drawable.outline_child_care_24),
            text = stringResource(id = R.string.tab_for_child)
        ),
        NavigationItemContent(
            storeCategoryType = StoreCategoryType.CoffeeShop,
            icon = ImageVector.vectorResource(id = R.drawable.outline_coffee_24),
            text = stringResource(id = R.string.tab_coffeeshop)
        )
    )

    if (navigationType == TravelCityNavigationType.PERMANENT_NAVIGATION_DRAWER) {
        val navigationDrawerContentDescription = stringResource(R.string.navigation_drawer)
        PermanentNavigationDrawer(
            drawerContent = {
                PermanentDrawerSheet(
                    Modifier.width(dimensionResource(id = R.dimen.drawer_width))
                ) {
                    NavigationDrawerContent(
                        selectedDestination = trableCityUiState.currentStoreCategory,
                        onTabPressed = onTabPressed,
                        navigationItemContentList = navigationItemContentList
                    )
                }
            },
            modifier = Modifier.testTag(navigationDrawerContentDescription)
        ) {

        }
    } else {
        if (trableCityUiState.isShowingList) {

        }
        else {

        }
    }
}

@Composable
private fun MyTravelCityAppContent(
    navigationType: TravelCityNavigationType,
    contentType: TravelCityContentType,
    trableCityUiState: TravelCityUiState,
    onTabPressed: (StoreCategoryType) -> Unit,
    onStorePressed: (Store) -> Unit,
    navigationItemContentList: List<NavigationItemContent>,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Row(modifier = Modifier.fillMaxSize()) {
            AnimatedVisibility(visible = navigationType == TravelCityNavigationType.NAVIGATION_RAIL) {
                val navigationRailContentDescription = stringResource(R.string.navigation_rail)
                TravelCityNavigationRail(
                    currentTab = trableCityUiState.currentStoreCategory,
                    onTabPressed = onTabPressed,
                    navigationItemContentList = navigationItemContentList,
                    modifier = Modifier
                        .testTag(navigationRailContentDescription)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.inverseOnSurface)
            ) {
//                if (contentType == TravelCityContentType.ListAndDetail) {
//                    ReplyListAndDetailContent(
//                        replyUiState = replyUiState,
//                        onEmailCardPressed = onEmailCardPressed,
//                        modifier = Modifier.weight(1f),
//                    )
//                } else {
                    StoreListOnlyContent(
                        travelCityUiState = trableCityUiState,
                        onStorePressed = onStorePressed,
                        modifier = Modifier.weight(1f)
                            .padding(
                                horizontal = dimensionResource(R.dimen.email_list_only_horizontal_padding)
                            )
                    )
//                }
                AnimatedVisibility(
                    visible = navigationType == TravelCityNavigationType.BOTTOM_NAVIGATION
                ) {
                    val bottomNavigationContentDescription = stringResource(R.string.navigation_bottom)
                    TravelCityBottomNavigationBar(
                        currentTab = trableCityUiState.currentStoreCategory,
                        onTabPressed = onTabPressed,
                        navigationItemContentList = navigationItemContentList,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag(bottomNavigationContentDescription)
                    )
                }
            }
        }
    }
}

@Composable
private fun TravelCityNavigationRail(
    currentTab: StoreCategoryType,
    onTabPressed: ((StoreCategoryType) -> Unit),
    navigationItemContentList: List<NavigationItemContent>,
    modifier: Modifier = Modifier
) {
    NavigationRail(modifier = modifier) {
        for (navItem in navigationItemContentList) {
            NavigationRailItem(
                selected = currentTab == navItem.storeCategoryType,
                onClick = { onTabPressed(navItem.storeCategoryType) },
                icon = {
                    Icon(
                        imageVector = navItem.icon,
                        contentDescription = navItem.text
                    )
                }
            )
        }
    }
}

@Composable
private fun TravelCityBottomNavigationBar(
    currentTab: StoreCategoryType,
    onTabPressed: ((StoreCategoryType) -> Unit),
    navigationItemContentList: List<NavigationItemContent>,
    modifier: Modifier = Modifier
) {
    NavigationBar(modifier = modifier) {
        for (navItem in navigationItemContentList) {
            NavigationBarItem(
                selected = currentTab == navItem.storeCategoryType,
                onClick = { onTabPressed(navItem.storeCategoryType) },
                icon = {
                    Icon(
                        imageVector = navItem.icon,
                        contentDescription = navItem.text
                    )
                }
            )
        }
    }
}

@Composable
private fun NavigationDrawerContent(
    selectedDestination: StoreCategoryType,
    onTabPressed: ((StoreCategoryType) -> Unit),
    navigationItemContentList: List<NavigationItemContent>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        NavigationDrawerHeader(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.profile_image_padding)),
        )
        for (navItem in navigationItemContentList) {
            NavigationDrawerItem(
                selected = selectedDestination == navItem.storeCategoryType,
                label = {
                    Text(
                        text = navItem.text,
                        modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.drawer_padding_header))
                    )
                },
                icon = {
                    Icon(
                        imageVector = navItem.icon,
                        contentDescription = navItem.text
                    )
                },
                colors = NavigationDrawerItemDefaults.colors(
                    unselectedContainerColor = Color.Transparent
                ),
                onClick = { onTabPressed(navItem.storeCategoryType) }
            )
        }
    }
}

@Composable
private fun NavigationDrawerHeader(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TravelCityLogo(modifier = Modifier.size(dimensionResource(R.dimen.reply_logo_size)))
    }
}

private data class NavigationItemContent(
    val storeCategoryType: StoreCategoryType,
    val icon: ImageVector,
    val text: String
)


@Preview
@Composable
fun StoresListItemPreview() {
    MyTravelCityTheme {
//        StoresListItem(
//            sport = LocalSportsDataProvider.defaultSport,
//            onItemClick = {}
//        )
    }
}

@Preview
@Composable
fun StoresListPreview() {
    MyTravelCityTheme {
        Surface {
//            StoresList(
//                sports = LocalSportsDataProvider.getSportsData(),
//                onClick = {},
//            )
        }
    }
}

@Preview(device = Devices.TABLET)
@Composable
fun StoresListAndDetailsPreview() {
    MyTravelCityTheme {
        Surface {
//            StoresListAndDetail(
//                sports = LocalSportsDataProvider.getSportsData(),
//                selectedSport = LocalSportsDataProvider.getSportsData().getOrElse(0) {
//                    LocalSportsDataProvider.defaultSport
//                },
//                onClick = {},
//                onBackPressed = {},
//            )
        }
    }
}
