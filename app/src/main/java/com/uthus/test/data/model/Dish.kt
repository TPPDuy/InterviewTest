package com.uthus.test.data.model
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
@Entity(
    tableName = "Dish"
)
data class Dish(
    @PrimaryKey
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "quantity")
    val quantity: String,
    @ColumnInfo(name = "calories")
    val calories: Int,
    @ColumnInfo(name = "expiry")
    val expiry: String,
    @ColumnInfo(name = "numOfSelected")
    val numOfSelected: Int = 0,
): Serializable
