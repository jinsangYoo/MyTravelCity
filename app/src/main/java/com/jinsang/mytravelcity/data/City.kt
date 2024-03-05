package com.jinsang.mytravelcity.data

import androidx.annotation.DrawableRes

data class City(
    val id: Int,
    val name: String,
    val description: String,
    @DrawableRes val imageResourceId: Int,
)
