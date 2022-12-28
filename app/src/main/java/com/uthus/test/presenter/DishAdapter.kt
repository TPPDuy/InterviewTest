package com.uthus.test.presenter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.uthus.test.R
import com.uthus.test.data.model.Dish

class DishAdapter(
    private val onChangeSelectedState: (Dish) -> Unit
): RecyclerView.Adapter<DishAdapter.DishViewHolder>() {

    private val mData: MutableList<DisplayingDish> = mutableListOf()

    fun submitList(data: List<DisplayingDish>) {
        mData.clear()
        mData.addAll(data)

        // Affect to performance a lot. Should change to use DiffUtil.Callback in future
        notifyDataSetChanged()
    }

    fun getData(): List<DisplayingDish> {
        return mData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_dish_layout, parent, false)
        return DishViewHolder(view)
    }

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        val displayingDish = mData[position]
        val dish = displayingDish.dish
        holder.groupAdjustSelectedNumber.isVisible = displayingDish.isSelected
        holder.checkBox.isChecked = displayingDish.isSelected
        holder.dishNameTextView.text = dish.name
        holder.dishQuantitiesTextView.text = dish.quantity
        holder.dishCaloriesTextView.text = "${dish.calories} kcal"
        holder.checkBox.setOnClickListener{ _ ->
            onChangeSelectedState.invoke(dish)
        }

        holder.buttonRemove.setOnClickListener {
            if (dish.numOfSelected > 0) {
                dish.numOfSelected--
                holder.selectedNumberTextView.text = dish.numOfSelected.toString()
            }
        }
        holder.buttonAdd.setOnClickListener {
            dish.numOfSelected++
            holder.selectedNumberTextView.text = dish.numOfSelected.toString()
        }
        holder.selectedNumberTextView.text = dish.numOfSelected.toString()
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    class DishViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val checkBox: CheckBox
        val dishNameTextView: TextView
        val dishQuantitiesTextView: TextView
        val dishCaloriesTextView: TextView
        val buttonRemove: ImageButton
        val buttonAdd: ImageButton
        val selectedNumberTextView: TextView
        val groupAdjustSelectedNumber: View

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
}