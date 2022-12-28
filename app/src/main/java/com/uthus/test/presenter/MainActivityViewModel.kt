package com.uthus.test.presenter

import androidx.lifecycle.*
import com.uthus.test.data.model.Dish
import com.uthus.test.domain.DishRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val dishRepository: DishRepository
): ViewModel() {

    private val _dishes = MutableLiveData<List<DisplayingDish>>()
    val dishes: LiveData<List<DisplayingDish>> = _dishes

    private val _modifyingDishes = MutableLiveData<MutableList<Dish>>(mutableListOf())
    val modifyingDishes: LiveData<MutableList<Dish>> = _modifyingDishes

    init {
        fetchDishesData()
    }

    private fun fetchDishesData() {
        viewModelScope.launch(Dispatchers.IO) {
            val selectedDishes = dishRepository.getSelectedDishes()
            val data = dishRepository.getFakeDishesData()

            val selectedDishesMap = selectedDishes.associate {
                Pair(it.name, it.numOfSelected)
            }

            val mappedData = data.map {
                val selectedNumber = selectedDishesMap[it.name]
                if (selectedNumber != null) {
                    it.numOfSelected = selectedNumber
                }
                DisplayingDish(it, false)
            }

            _dishes.postValue(mappedData)
        }
    }


    /*fun updateSelectedDishes(selectedDish: Dish, updatedValue: Int) {
        if (updatedValue < 0) return
        val modifyingData = modifyingDishes.value ?: return
        for (item in modifyingData) {
            if (item.name == selectedDish.name) {
                item.numOfSelected = updatedValue
                _modifyingDishes.value = modifyingData
                return
            }
        }
        selectedDish.numOfSelected = updatedValue
        modifyingData.add(selectedDish)
    }*/

    fun selectDish(dish: Dish) {
        val currentData = _dishes.value ?: return
        for (item in currentData) {
            if (item.dish.name == dish.name) {
                item.isSelected = true
                _dishes.value = currentData
                return
            }
        }
    }

    fun unselectDish(dish: Dish) {
        val currentData = _dishes.value ?: return
        for (item in currentData) {
            if (item.dish.name == dish.name) {
                item.isSelected = false
                _dishes.value = currentData
                return
            }
        }
    }
}