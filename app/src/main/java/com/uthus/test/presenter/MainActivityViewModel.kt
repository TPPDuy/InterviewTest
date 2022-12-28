package com.uthus.test.presenter

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uthus.test.data.model.Dish
import com.uthus.test.domain.DishRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val dishRepository: DishRepository
): ViewModel() {

    private val _dishes = MutableLiveData<DataResult<List<Dish>>>()
    val dishes: LiveData<DataResult<List<Dish>>> = _dishes

    init {
        fetchDishesData()
    }

    private fun fetchDishesData() {
        viewModelScope.launch(Dispatchers.IO) {
            combine(
                dishRepository.getFakeDishesData(),
                dishRepository.getSelectedDishes()
            ) { fakeDishesData, selectedDishes ->
                // merge to 1 list
                emptyList<Dish>()
            }.onStart {
                _dishes.postValue(DataResult.loading(null))
            }.collect {
                _dishes.postValue(DataResult.success(it))
            }
        }
    }

    fun updateSelectedDishes(selectedDish: List<Dish>) {

    }
}