package com.uthus.test.presenter

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

class DishAdapter(): RecyclerView.Adapter<DishAdapter.DishViewHolder>() {

    private val mData: MutableList<DisplayingDish> = mutableListOf()

    fun submitList(data: List<DisplayingDish>) {
        val diffResult = DiffUtil.calculateDiff(DisplayingDishDiff(mData, data))
        diffResult.dispatchUpdatesTo(this)
        mData.clear()
        mData.addAll(data)
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
        holder.groupAdjustSelectedNumber.isVisible = false
        holder.checkBox.isChecked = displayingDish.isSelected
        holder.dishNameTextView.text = dish.name
        holder.dishQuantitiesTextView.text = dish.quantity
        holder.dishCaloriesTextView.text = "${dish.calories} kcal"
        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            holder.groupAdjustSelectedNumber.isVisible = isChecked
            displayingDish.isSelected = isChecked
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

    class DisplayingDishDiff(
        private val oldList: List<DisplayingDish>,
        private val newList: List<DisplayingDish>
    ): DiffUtil.Callback() {

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return try {
                val oldItem = oldList[oldItemPosition]
                val newItem = newList[newItemPosition]
                oldItem == newItem
            } catch (ex: Exception) {
                false
            }
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return try {
                val oldItem = oldList[oldItemPosition]
                val newItem = newList[newItemPosition]
                oldItem == newItem
            } catch (ex: Exception) {
                false
            }
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun getOldListSize(): Int {
            return oldList.size
        }
    }
}