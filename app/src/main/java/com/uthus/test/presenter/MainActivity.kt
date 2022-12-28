package com.uthus.test.presenter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.uthus.test.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        onSyncData()
    }

    private fun onSyncData() {
        viewModel.dishes.observe(this) { dataResult ->
            when(dataResult.status) {
                DataResult.Status.LOADING -> {
                    Toast.makeText(this@MainActivity, "Loading!", Toast.LENGTH_LONG).show()
                }
                DataResult.Status.SUCCESS -> {
                    Toast.makeText(this@MainActivity, "Fetch data successfully!", Toast.LENGTH_LONG).show()
                }
                DataResult.Status.ERROR -> {
                    Toast.makeText(this@MainActivity, "Error while fetching data!", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}