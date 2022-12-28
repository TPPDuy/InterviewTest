package com.uthus.test.di

import android.content.Context
import com.uthus.test.data.database.CoreDatabase
import com.uthus.test.data.database.DishDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object HiltDaoModule {

    // core database
    @Provides
    @Singleton
    fun providesCoreDatabase(
        @ApplicationContext context: Context
    ): CoreDatabase {
        return CoreDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun providesDishDao(
        database: CoreDatabase
    ): DishDao {
        return database.getDishDao()
    }
}