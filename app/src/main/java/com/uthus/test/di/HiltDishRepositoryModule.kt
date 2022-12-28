package com.uthus.test.di

import com.uthus.test.data.database.DishDao
import com.uthus.test.data.repository.DishRepositoryImpl
import com.uthus.test.data.storage.DishStorage
import com.uthus.test.data.storage.DishStorageImpl
import com.uthus.test.domain.DishRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object HiltDishRepositoryModule {

    @Singleton
    @Provides
    fun providesDishStorage(
        dishDao: DishDao
    ): DishStorage {
        return DishStorageImpl(dishDao)
    }

    @Singleton
    @Provides
    fun providesDishRepository(
        dishStorage: DishStorage
    ): DishRepository {
        return DishRepositoryImpl(dishStorage)
    }
}