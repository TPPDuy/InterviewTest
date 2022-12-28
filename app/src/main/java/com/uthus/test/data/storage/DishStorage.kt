package com.uthus.test.data.storage

import com.uthus.test.data.model.Dish
import kotlinx.coroutines.flow.Flow

interface DishStorage {
    suspend fun getFakeDishesData(): List<Dish>

    suspend fun getSelectedDishes(): List<Dish>

    suspend fun insertSelectedDishes(selectedDishes: List<Dish>): Array<Long>
    suspend fun updateSelectedDishes(selectedDishes: List<Dish>): Int

    /**
     * If a dish is already existed in DB -> update its value
     * Else -> insert to Db
     * ->> That's why I call this function is "upsert"
     */
    suspend fun upsertSelectedDishes(selectedDishes: List<Dish>): Int
}