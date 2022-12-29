package com.uthus.test.presenter

import com.uthus.test.data.model.Dish

/**
 * This class is used for showing Dish to UI with selected state
 */
data class DisplayingDish(
    val dish: Dish,
    var isSelected: Boolean = false
)