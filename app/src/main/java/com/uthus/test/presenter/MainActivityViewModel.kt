package com.uthus.test.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uthus.test.data.model.Dish
import com.uthus.test.domain.DishRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
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
                val selectedMap = selectedDishes.associate {
                    Pair(it.name, it.numOfSelected)
                }

                fakeDishesData.map {
                    val selectedNumber = selectedMap[it.name]
                    if (selectedNumber != null) {
                        it.copy(
                            numOfSelected = selectedNumber
                        )
                    } else {
                        it
                    }
                }
            }.onStart {
                _dishes.postValue(DataResult.loading(null))
            }.catch { e ->
                _dishes.postValue(DataResult.error(e))
            }.collect {
                _dishes.postValue(DataResult.success(it))
            }
        }
    }

    fun updateSelectedDishes(selectedDish: List<Dish>) {

    }
}