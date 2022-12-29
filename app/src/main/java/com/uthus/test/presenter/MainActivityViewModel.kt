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

    // Because it is LiveData so whenever our MainActivity is recreated -> it observes this event and show message "Save data successfully" or "Save data fail"
    // -> It makes user feels confused "I did save data successfully, but when I rotate my screen, app shows this message again!!!!"
    // -> That's why I create a helper class called "SingleEvent". In MainActivity, we only handle event that is not handled yet, else just ignore it
    private val _savingResultEvent = MutableLiveData<SingleEvent<Boolean>>()
    val savingResultEvent: LiveData<SingleEvent<Boolean>> = _savingResultEvent

    init {
        fetchDishesData()
    }

    // The solution works well in case we only need to read selected items in DB once right-away launching app
    // If must observe selected items from DB. This solution is bankrupt
    private fun fetchDishesData() {
        viewModelScope.launch(Dispatchers.IO) {
            val selectedDishes = dishRepository.getSelectedDishes()
            val data = dishRepository.getFakeDishesData()

            val selectedDishesMap = selectedDishes.associate {
                Pair(it.name, it.numOfSelected)
            }

            // The code below help me to show the latest selected number (that we saved in DB) if user select this dish again
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
            _savingResultEvent.postValue(SingleEvent(result))
        }
    }
}