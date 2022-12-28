package com.uthus.test.presenter

import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.uthus.test.R
import com.uthus.test.data.model.Dish

class DishAdapter: ListAdapter<Dish, DishAdapter.DishViewHolder>(
    object: DiffUtil.ItemCallback<Dish>() {
        override fun areItemsTheSame(oldItem: Dish, newItem: Dish): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Dish, newItem: Dish): Boolean {
            return oldItem.name == newItem.name && oldItem.quantity == newItem.quantity && oldItem.calories == newItem.calories
        }
    }
) {

    class DishViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val checkBox: CheckBox
        private val dishNameTextView: TextView
        private val dishQuantitiesTextView: TextView
        private val dishCaloriesTextView: TextView
        private val buttonRemove: Button
        private val buttonAdd: Button
        private val selectedNumberTextView: TextView
        private val groupAdjustSelectedNumber: View

        init {
            checkBox = view.findViewById(R.id.checkbox)
            dishNameTextView = view.findViewById(R.id.dish_name)
            dishQuantitiesTextView = view.findViewById(R.id.dish_quantities)
            dishCaloriesTextView = view.findViewById(R.id.dish_calories)
            buttonRemove = view.findViewById(R.id.btn_remove)
            buttonAdd = view.findViewById(R.id.btn_add)
            selectedNumberTextView = view.findViewById(R.id.text_selected_number)
            groupAdjustSelectedNumber = view.findViewById(R.id.group_adjust_selected_number)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}