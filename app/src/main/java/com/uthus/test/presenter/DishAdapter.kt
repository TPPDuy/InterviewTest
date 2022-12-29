package com.uthus.test.presenter

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.uthus.test.R
import java.text.SimpleDateFormat
import java.util.*

// I use RecyclerView.ListAdapter instead of RecyclerView.Adapter bcs I want to make use of DiffUtil.ItemCallback for better performance when rendering
// Of course I can use DiffUtil with normal RecyclerView.Adapter, but using ListAdapter is easier
class DishAdapter(): ListAdapter<DisplayingDish, DishAdapter.DishViewHolder>(object: DiffUtil.ItemCallback<DisplayingDish>() {
    override fun areContentsTheSame(oldItem: DisplayingDish, newItem: DisplayingDish): Boolean {
        return oldItem.dish == newItem.dish
    }

    override fun areItemsTheSame(oldItem: DisplayingDish, newItem: DisplayingDish): Boolean {
        return oldItem == newItem
    }
}) {
    val formatter = SimpleDateFormat("HH:mm:ss, dd-MM-yyyy", Locale.getDefault())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_dish_layout, parent, false)
        return DishViewHolder(view)
    }

    override fun onViewRecycled(holder: DishViewHolder) {
        super.onViewRecycled(holder)
        holder.handler.removeCallbacksAndMessages(null)
    }

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        val displayingDish = getItem(position)
        holder.bindData(displayingDish)
    }

    inner class DishViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val checkBox: CheckBox
        private val dishNameTextView: TextView
        private val dishQuantitiesTextView: TextView
        private val dishCaloriesTextView: TextView
        private val dishExpiryTextView: TextView
        private val buttonRemove: ImageButton
        private val buttonAdd: ImageButton
        private val selectedNumberTextView: TextView
        private val groupAdjustSelectedNumber: View

        val handler: Handler = Handler(Looper.getMainLooper())

        init {
            checkBox = view.findViewById(R.id.checkbox)
            dishNameTextView = view.findViewById(R.id.dish_name)
            dishQuantitiesTextView = view.findViewById(R.id.dish_quantities)
            dishCaloriesTextView = view.findViewById(R.id.dish_calories)
            dishExpiryTextView = view.findViewById(R.id.dish_expiry)
            buttonRemove = view.findViewById(R.id.btn_remove)
            buttonAdd = view.findViewById(R.id.btn_add)
            selectedNumberTextView = view.findViewById(R.id.text_selected_number)
            groupAdjustSelectedNumber = view.findViewById(R.id.group_adjust_selected_number)
        }

        
        fun bindData(displayingDish: DisplayingDish) {
            val dish = displayingDish.dish
            groupAdjustSelectedNumber.isVisible = displayingDish.isSelected
            checkBox.isChecked = displayingDish.isSelected //isSelected
            dishNameTextView.text = dish.name
            dishQuantitiesTextView.text = dish.quantity
            dishCaloriesTextView.text = "${dish.calories} kcal"
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                groupAdjustSelectedNumber.isVisible = isChecked
                displayingDish.isSelected = isChecked
            }

            // By using the same reference to data in view model, we can adjust the "numOfSelected" value of each items without using additional callback
            buttonRemove.setOnClickListener {
                if (dish.numOfSelected > 0) {
                    dish.numOfSelected--
                    selectedNumberTextView.text = dish.numOfSelected.toString()
                }
            }
            buttonAdd.setOnClickListener {
                dish.numOfSelected++
                selectedNumberTextView.text = dish.numOfSelected.toString()
            }
            selectedNumberTextView.text = dish.numOfSelected.toString()

            val expirationDate = try {
                formatter.parse(dish.expiry)
            } catch (ex: Exception) {
                null
            }

            val runnable = object: Runnable {
                override fun run() {
                    if (expirationDate != null) {
                        if (expirationDate.time > System.currentTimeMillis()) {
                            dishExpiryTextView.text = "Expired"
                        } else {
                            dishExpiryTextView.text = "Expired in: ${expirationDate.countDownTime()}"
                            handler.postDelayed(this, 1000)
                        }
                    } else {
                        dishExpiryTextView.isVisible = false
                    }
                }
            }

            handler.post(runnable)
        }
    }
}