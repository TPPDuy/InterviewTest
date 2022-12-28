package com.uthus.test.presenter

import androidx.lifecycle.*
import com.uthus.test.data.model.Dish
import com.uthus.test.domain.DishRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.EventObject
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val dishRepository: DishRepository
): ViewModel() {

    private val _dishes = MutableLiveData<List<DisplayingDish>>()
    val dishes: LiveData<List<DisplayingDish>> = _dishes

    private val _savingResultEvent = MutableLiveData<Boolean>()
    val savingResultEvent: LiveData<Boolean> = _savingResultEvent

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

    fun saveSelectedDishes(selectedDishes: List<Dish>) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = dishRepository.saveSelectedDishes(selectedDishes)
            _savingResultEvent.postValue(result)
        }
    }
}