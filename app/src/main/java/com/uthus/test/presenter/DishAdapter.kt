package com.uthus.test.presenter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.uthus.test.R
import com.uthus.test.data.model.Dish

class DishAdapter(
    onChangeSelectedNumber: (Dish, Int, Int) -> Unit
): ListAdapter<Dish, DishAdapter.DishViewHolder>(
    object: DiffUtil.ItemCallback<Dish>() {
        override fun areItemsTheSame(oldItem: Dish, newItem: Dish): Boolean {
            return false//oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Dish, newItem: Dish): Boolean {
            return false//oldItem.name == newItem.name && oldItem.quantity == newItem.quantity && oldItem.calories == newItem.calories
        }
    }
) {

    class DishViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val checkBox: CheckBox
        private val dishNameTextView: TextView
        private val dishQuantitiesTextView: TextView
        private val dishCaloriesTextView: TextView
        private val buttonRemove: ImageButton
        private val buttonAdd: ImageButton
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
            groupAdjustSelectedNumber.isVisible = false
        }

        fun bindData(dish: Dish) {
            dishNameTextView.text = dish.name
            dishQuantitiesTextView.text = dish.quantity
            dishCaloriesTextView.text = "${dish.calories} kcal"
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                groupAdjustSelectedNumber.isVisible = isChecked
            }
            buttonRemove.setOnClickListener {  }
            buttonAdd.setOnClickListener {  }
            selectedNumberTextView.text = dish.numOfSelected.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_dish_layout, parent, false)
        return DishViewHolder(view)
    }

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        val dish = getItem(position)
        holder.bindData(dish)
    }
}