package com.jinsang.mytravelcity.data

data class Store(
    val id: Long,
    val name: String = "empty name",
    val description: String? = null,
    val category: StoreCategoryType = StoreCategoryType.Food,
    val urlRef: List<UrlRef> = emptyList()
)
