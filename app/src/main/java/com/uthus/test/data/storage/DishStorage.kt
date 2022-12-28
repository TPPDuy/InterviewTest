package com.uthus.test.data.storage

import com.uthus.test.data.model.Dish
import kotlinx.coroutines.flow.Flow

interface DishStorage {
    fun getFakeDishesData(): Flow<List<Dish>>

    fun getSelectedDishes(): Flow<List<Dish>>

    fun insertSelectedDishes(selectedDishes: List<Dish>): Array<Long>
    fun updateSelectedDishes(selectedDishes: List<Dish>): Int

    /**
     * If a dish is already existed in DB -> update its value
     * Else -> insert to Db
     * ->> That's why I call this function is "upsert"
     */
    fun upsertSelectedDishes(selectedDishes: List<Dish>): Int
}