package com.uthus.test.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.uthus.test.data.model.Dish

@Database(
    entities = [
        Dish::class
    ],
    version = 1,
    exportSchema = false
)
abstract class CoreDatabase: RoomDatabase() {

    abstract fun getDishDao(): DishDao

    companion object {
        private const val DATABASE_NAME = "Core_database"
        @Volatile
        private var instance: CoreDatabase? = null

        fun getDatabase(context: Context): CoreDatabase {
            val currentInstance = instance
            if (currentInstance != null) {
                return currentInstance
            }

            synchronized(this) {
                val tempInstance = Room.databaseBuilder(context.applicationContext, CoreDatabase::class.java, DATABASE_NAME)
                    .setJournalMode(JournalMode.TRUNCATE)
                    .fallbackToDestructiveMigration()
                    .build()

                instance = tempInstance
                return tempInstance
            }
        }
    }
}