package com.jinsang.mytravelcity.ui.utils

/**
 * Different type of navigation supported by app depending on size and state.
 */
enum class TravelCityNavigationType {
    BOTTOM_NAVIGATION, NAVIGATION_RAIL, PERMANENT_NAVIGATION_DRAWER
}

/**
 * Content shown depending on size and state of device.
 */
enum class TravelCityContentType {
    ListOnly, ListAndDetail
}
