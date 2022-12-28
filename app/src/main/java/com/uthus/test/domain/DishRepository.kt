package com.uthus.test.domain

import com.uthus.test.data.model.Dish
import kotlinx.coroutines.flow.Flow

interface DishRepository {
    suspend fun getFakeDishesData(): List<Dish>
    suspend fun getSelectedDishes(): List<Dish>
    suspend fun saveSelectedDishes(selectedDishes: List<Dish>): Boolean
}