package com.uthus.test.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.uthus.test.data.model.Dish
import kotlinx.coroutines.flow.Flow

@Dao
abstract class DishDao {
    @Transaction
    @Query("SELECT * FROM Dish")
    abstract suspend fun getSelectedDishes(): List<Dish>

    @Update(entity = Dish::class)
    abstract suspend fun updateSelectedDishes(selectedDishes: List<Dish>): Int

    // By using OnConflictStrategy.REPLACE, we don't need to check if data is existed to update or do insert in vice versa
    // Just replace by the newest data
    @Insert(entity = Dish::class, onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertSelectedDishes(selectedDishes: List<Dish>): Array<Long>
}