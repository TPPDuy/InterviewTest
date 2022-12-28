package com.uthus.test.data.storage

import com.uthus.test.data.database.DishDao
import com.uthus.test.data.model.Dish
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DishStorageImpl(
    private val dishDao: DishDao
): DishStorage {
    override fun getFakeDishesData(): Flow<List<Dish>> {
        return flow {
            emit(emptyList())
        }
    }

    override fun getSelectedDishes(): Flow<List<Dish>> {
        return flow {
            emit(emptyList())
        }
    }

    override fun insertSelectedDishes(selectedDishes: List<Dish>): Array<Long> {
        return emptyArray()
    }

    override fun updateSelectedDishes(selectedDishes: List<Dish>): Int {
        return 0
    }

    override fun upsertSelectedDishes(selectedDishes: List<Dish>): Int {
        return 0
    }
}