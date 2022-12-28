package com.uthus.test.presenter

import com.uthus.test.data.model.Dish

data class DisplayingDish(
    val dish: Dish,
    var isSelected: Boolean = false
)