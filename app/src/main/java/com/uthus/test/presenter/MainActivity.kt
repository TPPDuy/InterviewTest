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
        dishAdapter = DishAdapter() { selectedDish, numOfSelected, position ->

        }

        binding.recyclerView.apply {
            adapter = dishAdapter
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun onSyncData() {
        viewModel.dishes.observe(this) { dataResult ->
            when(dataResult.status) {
                DataResult.Status.LOADING -> {
                    binding.progress.isVisible = true
                    binding.btnSave.isVisible = false
                }
                DataResult.Status.SUCCESS -> {
                    binding.progress.isVisible = false
                    binding.btnSave.isVisible = true
                    binding.btnSave.isEnabled = true
                    dishAdapter.submitList(dataResult.data ?: emptyList())
                }
                DataResult.Status.ERROR -> {
                    binding.progress.isVisible = false
                    binding.btnSave.isVisible = true
                    binding.btnSave.isEnabled = false
                }
            }
        }
    }
}