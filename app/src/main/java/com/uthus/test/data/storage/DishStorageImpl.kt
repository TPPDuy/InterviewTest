package com.uthus.test.data.storage

import com.uthus.test.data.database.DishDao
import com.uthus.test.data.model.Dish
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DishStorageImpl(
    private val dishDao: DishDao
): DishStorage {
    private val fakeData = listOf(
        Dish(
            name = "Acai Juice",
            quantity = "8 fl. oz. (240ml)",
            calories = 139,
            expiry = "00:00:00, 01-01-2023"
        ),
        Dish(
            name = "Aloe Vera",
            quantity = "8 fl. oz. (240ml)",
            calories = 106,
            expiry = "00:00:00, 01-02-2023"
        ),
        Dish(
            name = "Apple Juice",
            quantity = "8 fl. oz. (240ml)",
            calories = 110,
            expiry = "00:00:00, 01-03-2023"
        ),
        Dish(
            name = "Apricot Nectar",
            quantity = "8 fl. oz. (240ml)",
            calories = 134,
            expiry = "00:00:00, 01-04-2023"
        ),
        Dish(
            name = "Banana Juice",
            quantity = "8 fl. oz. (240ml)",
            calories = 120,
            expiry = "00:00:00, 01-05-2023"
        ),
        Dish(
            name = "Blackberry Juice",
            quantity = "8 fl. oz. (240ml)",
            calories = 115,
            expiry = "00:00:00, 01-06-2023"
        ),
        Dish(
            name = "Boysenberry Juice",
            quantity = "8 fl. oz. (240ml)",
            calories = 130,
            expiry = "00:00:00, 01-07-2023"
        ),
        Dish(
            name = "Capri-Sun",
            quantity = "1 capri-sun (200ml)",
            calories = 82,
            expiry = "00:00:00, 01-08-2023"
        ),
        Dish(
            name = "Carrot Juice",
            quantity = "8 fl. oz. (240ml)",
            calories = 96,
            expiry = "00:00:00, 01-09-2023"
        ),
        Dish(
            name = "Chamomile Tea",
            quantity = "8 fl. oz. (240ml)",
            calories = 0,
            expiry = "00:00:00, 01-10-2023"
        )
    )
    override fun getFakeDishesData(): Flow<List<Dish>> {
        return flow {
            emit(fakeData)
        }
    }

    override fun getSelectedDishes(): Flow<List<Dish>> {
        return dishDao.getSelectedDishes()
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