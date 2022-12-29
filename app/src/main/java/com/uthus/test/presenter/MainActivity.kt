package com.uthus.test.presenter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.uthus.test.R
import com.uthus.test.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var dishAdapter: DishAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        onSyncView()
        onSyncData()
    }

    private fun onSyncView() {
        dishAdapter = DishAdapter()

        binding.recyclerView.apply {
            adapter = dishAdapter
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        }

        binding.btnSave.setOnClickListener {
            val selectedDishes = dishAdapter.currentList.filter { it.isSelected }.map { it.dish }
            viewModel.saveSelectedDishes(selectedDishes)
        }
    }

    private fun onSyncData() {
        viewModel.dishes.observe(this) { data ->
            dishAdapter.submitList(data ?: emptyList())
        }

        viewModel.savingResultEvent.observe(this) {
            val isSuccess = it.getContentIfNotHandled() ?: return@observe
            if (isSuccess) {
                Toast.makeText(this@MainActivity, "Save data successfully!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@MainActivity, "Save data fail!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}