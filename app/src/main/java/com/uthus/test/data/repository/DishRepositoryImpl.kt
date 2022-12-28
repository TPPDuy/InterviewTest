package com.uthus.test.data.repository

import com.uthus.test.data.model.Dish
import com.uthus.test.data.storage.DishStorage
import com.uthus.test.domain.DishRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DishRepositoryImpl(
    private val dishStorage: DishStorage
): DishRepository {
    override fun getFakeDishesData(): Flow<List<Dish>> {
        return dishStorage.getFakeDishesData()
    }
    override fun getSelectedDishes(): Flow<List<Dish>> {
        return dishStorage.getSelectedDishes()
    }

    override fun saveSelectedDishes(selectedDishes: List<Dish>): Boolean {
        return false
    }
}