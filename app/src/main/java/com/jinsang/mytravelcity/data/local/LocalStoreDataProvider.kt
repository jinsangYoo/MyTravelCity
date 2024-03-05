package com.jinsang.mytravelcity.data.local

import com.jinsang.mytravelcity.data.Store
import com.jinsang.mytravelcity.data.StoreCategoryType

object LocalStoreDataProvider {
    val allStores = listOf(
        Store(
            id = 0L,
            name = "우진해장국",
            category = StoreCategoryType.Food
        ),
        Store(
            id = 1L,
            name = "오설록",
            category = StoreCategoryType.CoffeeShop
        ),
        Store(
            id = 2L,
            name = "가시림",
            category = StoreCategoryType.CoffeeShop
        ),
        Store(
            id = 3L,
            name = "말타기",
            category = StoreCategoryType.ForChild
        ),
        Store(
            id = 4L,
            name = "올레시장",
            category = StoreCategoryType.Shopping
        ),
        Store(
            id = 5L,
            name = "삼방산",
            category = StoreCategoryType.Park
        ),
    )

    fun get(id: Long): Store? {
        return allStores.firstOrNull { it.id == id }
    }

    val defaultStore = Store(
        id = -1,
    )
}