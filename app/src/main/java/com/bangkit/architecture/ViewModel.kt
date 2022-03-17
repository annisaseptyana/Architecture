package com.bangkit.architecture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.bangkit.architecture.databinding.ActivityViewModelBinding

class ViewModel : AppCompatActivity() {

    private lateinit var binding: ActivityViewModelBinding
    private val viewModel: MainViewModel by viewModels()

    private fun displayResult() {
        // Without LiveData, we need to update the result using this function
        binding.tvResult.text = viewModel.result.toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewModelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayResult()

        binding.btnCalculate.setOnClickListener {
            val width = binding.edtWidth.text.toString()
            val height = binding.edtHeight.text.toString()
            val length = binding.edtLength.text.toString()

            when {
                width.isEmpty() -> {
                    binding.edtWidth.error = "This must be filled"
                }
                height.isEmpty() -> {
                    binding.edtHeight.error = "This must be filled"
                }
                length.isEmpty() -> {
                    binding.edtLength.error = "This must be filled"
                }
                else -> {
                    viewModel.calculate(width, height, length)
                    displayResult()
                }
            }
        }
    }
}