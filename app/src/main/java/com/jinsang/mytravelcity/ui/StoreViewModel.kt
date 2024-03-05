package com.jinsang.mytravelcity.ui

import androidx.lifecycle.ViewModel
import com.jinsang.mytravelcity.data.Store
import com.jinsang.mytravelcity.data.StoreCategoryType
import com.jinsang.mytravelcity.data.local.LocalStoreDataProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class TravelCityViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(TravelCityUiState())
    val uiState: StateFlow<TravelCityUiState> = _uiState

    init {
        initializeUIState()
    }

    private fun initializeUIState() {
        val storeBoxed: Map<StoreCategoryType, List<Store>> =
            LocalStoreDataProvider.allStores.groupBy { it.category }
        _uiState.value = TravelCityUiState(
            storeboxes = storeBoxed,
            currentSelectedStore = storeBoxed[StoreCategoryType.Food]?.get(0) ?:
            LocalStoreDataProvider.defaultStore
        )
    }

    fun updateDetailsScreenStates(store: Store) {
        _uiState.update {
            it.copy(
                currentSelectedStore = store,
                isShowingList = false
            )
        }
    }

    fun resetHomeScreenStates() {
        _uiState.update {
            it.copy(
                currentSelectedStore = it.storeboxes[it.currentStoreCategory]?.get(0)
                    ?: LocalStoreDataProvider.defaultStore,
                isShowingList = true
            )
        }
    }

    fun updateCurrentStoreCategory(storeCategoryType: StoreCategoryType) {
        _uiState.update {
            it.copy(
                currentStoreCategory = storeCategoryType
            )
        }
    }
}

data class TravelCityUiState(
    val storeboxes: Map<StoreCategoryType, List<Store>> = emptyMap(),
    val currentStoreCategory: StoreCategoryType = StoreCategoryType.Food,
    val currentSelectedStore: Store = LocalStoreDataProvider.defaultStore,
    val isShowingList: Boolean = true
) {
    val currentStoresInStoreCategory: List<Store> by lazy { storeboxes[currentStoreCategory]!! }
}
