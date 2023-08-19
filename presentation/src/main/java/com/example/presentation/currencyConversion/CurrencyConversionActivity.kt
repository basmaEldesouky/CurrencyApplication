package com.example.presentation.currencyConversion

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.base.BaseActivity
import com.example.presentation.databinding.ActivityCurrencyConversionBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class CurrencyConversionActivity(): BaseActivity<ActivityCurrencyConversionBinding>() {

    override val bindLayout: (LayoutInflater) -> ActivityCurrencyConversionBinding
        get() = ActivityCurrencyConversionBinding::inflate

    private val viewModel: CurrencyConversionViewModel by viewModels()
    override fun prepareView() {
        binding.switchBtn.setOnClickListener{
            binding.fromCurrencyDropdown.setSelection(binding.toCurrencyDropdown.selectedItemPosition).also {
                binding.toCurrencyDropdown.setSelection(binding.fromCurrencyDropdown.selectedItemPosition)
            }
            viewModel.getCurrencyRates(binding.fromAmountET.toString(),
                "EUR",
                listOf(binding.fromCurrencyDropdown.selectedItem.toString(), binding.toCurrencyDropdown.selectedItem.toString()))

        }
        binding.fromAmountET.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                viewModel.getCurrencyRates(s as String,
                    "EUR",
                    listOf(binding.fromCurrencyDropdown.selectedItem.toString(), binding.toCurrencyDropdown.selectedItem.toString()))
            }

            override fun afterTextChanged(s: Editable) {}
        })
        binding.detailsBtn.setOnClickListener{

        }
        initObservers()
    }

    private fun initObservers(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.conversion.collect { event ->
                    when(event) {
                        is CurrencyConversionViewModel.CurrencyEvent.Loading -> {
                            binding.loadingLayout.root.isVisible = true
                        }
                        is CurrencyConversionViewModel.CurrencyEvent.Success -> {
                            binding.loadingLayout.root.isVisible = false
                            binding.toAmountET.setText(event.resultText)
                        }
                        is CurrencyConversionViewModel.CurrencyEvent.Failure -> {
                            binding.loadingLayout.root.isVisible = false
                            binding.errorLayout.root.isVisible = true

                        }
                        else -> Unit
                    }
                }
            }
        }
    }

}