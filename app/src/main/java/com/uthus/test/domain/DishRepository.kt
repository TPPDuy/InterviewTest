package com.uthus.test.domain

import com.uthus.test.data.model.Dish
import kotlinx.coroutines.flow.Flow

interface DishRepository {
    fun getFakeDishesData(): Flow<List<Dish>>
    fun getSelectedDishes(): Flow<List<Dish>>
    fun saveSelectedDishes(selectedDishes: List<Dish>): Boolean
}