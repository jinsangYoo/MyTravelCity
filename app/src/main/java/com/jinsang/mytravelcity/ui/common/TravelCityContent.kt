package com.jinsang.mytravelcity.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.jinsang.mytravelcity.R
import com.jinsang.mytravelcity.data.Store
import com.jinsang.mytravelcity.ui.TravelCityUiState

@Composable
fun StoreListOnlyContent(
    travelCityUiState: TravelCityUiState,
    onStorePressed: (Store) -> Unit,
    modifier: Modifier = Modifier
) {
    val stores = travelCityUiState.currentStoresInStoreCategory

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(R.dimen.email_list_item_vertical_spacing)
        )
    ) {
        item {
            StoreMainTopBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = dimensionResource(R.dimen.topbar_padding_vertical))
            )
        }
        items(stores, key = { store -> store.id }) { store ->
            TravelCityStoreListItem(
                store = store,
                selected = false,
                onCardClick = {
                    onStorePressed(store)
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TravelCityStoreListItem(
    store: Store,
    selected: Boolean,
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = if (selected) {
                MaterialTheme.colorScheme.primaryContainer
            } else {
                MaterialTheme.colorScheme.secondaryContainer
            }
        ),
        onClick = onCardClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.email_list_item_inner_padding))
        ) {
            Text(
                text = store.name,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(
                    top = dimensionResource(R.dimen.email_list_item_header_subject_spacing),
                    bottom = dimensionResource(R.dimen.email_list_item_subject_body_spacing)
                ),
            )
            Text(
                text = store.description ?: stringResource(id = R.string.empty_description),
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
private fun StoreMainTopBar(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        TravelCityLogo(
            modifier = Modifier
                .size(dimensionResource(R.dimen.topbar_logo_size))
                .padding(start = dimensionResource(R.dimen.topbar_logo_padding_start))
        )
//        ReplyProfileImage(
//            drawableResource = LocalAccountsDataProvider.defaultAccount.avatar,
//            description = stringResource(R.string.profile),
//            modifier = Modifier
//                .padding(end = dimensionResource(R.dimen.topbar_profile_image_padding_end))
//                .size(dimensionResource(R.dimen.topbar_profile_image_size))
//        )
    }
}

@Composable
fun TravelCityLogo(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary
) {
    Image(
        painter = painterResource(R.drawable.outline_travel_explore_24),
        contentDescription = stringResource(R.string.logo),
        colorFilter = ColorFilter.tint(color),
        modifier = modifier
    )
}