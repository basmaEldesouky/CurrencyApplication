package com.example.presentation.currencyConversion

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.common.utils.NetworkUtils.isNetworkAvailable
import com.example.common.utils.viewBinding
import com.example.presentation.R
import com.example.presentation.databinding.FragmentCurrencyConversionBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class CurrencyConversionFragment(): Fragment(R.layout.fragment_currency_conversion) {

    private val binding by viewBinding(FragmentCurrencyConversionBinding::bind)

    private val viewModel: CurrencyConversionViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.switchBtn.setOnClickListener{
            handleUiWhenSwitching()
            convertCurrencies(binding.fromAmountET.toString())
        }

        binding.fromAmountET.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                convertCurrencies(s.toString())
            }

            override fun afterTextChanged(s: Editable) {}
        })

        binding.detailsBtn.setOnClickListener{
            val bundle = bundleOf("fromCurrency" to binding.fromCurrencyDropdown.selectedItem.toString(),
                "toCurrency" to binding.fromCurrencyDropdown.selectedItem.toString())
            findNavController().navigate(
                R.id.action_currency_conversion_fragment_to_currency_rates_fragment, bundle)
        }

        binding.errorLayout.btnRetry.setOnClickListener{
            handleUiWhenRetrying()
        }

        initObservers()
    }

    private fun initObservers(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.conversion.collect { event ->
                    when(event) {
                        is CurrencyConversionViewModel.CurrencyEvent.Loading -> {
                            //binding.loadingLayout.root.isVisible = true
                            binding.mainLayout.isVisible = false
                        }
                        is CurrencyConversionViewModel.CurrencyEvent.Success -> {
                            //binding.loadingLayout.root.isVisible = false
                            binding.mainLayout.isVisible = true
                            binding.toAmountET.setText(event.resultText)
                        }
                        is CurrencyConversionViewModel.CurrencyEvent.Failure -> {
                            //binding.loadingLayout.root.isVisible = false
                            binding.errorLayout.root.isVisible = true
                            binding.mainLayout.isVisible = false
                        }
                        else -> Unit
                    }
                }
            }
        }
    }

    private fun convertCurrencies(amount: String){
        if (!isNetworkAvailable(requireContext())) {
            Toast.makeText(requireContext(), "No Internet Connection", Toast.LENGTH_LONG).show()
        }else {
            val fromSymbol = binding.fromCurrencyDropdown.selectedItem.toString()
            val toSymbol = binding.fromCurrencyDropdown.selectedItem.toString()
            val symbolsStr = "$fromSymbol, $toSymbol"
            viewModel.getCurrencyRates(amount, "EUR", symbolsStr)
        }
    }

    private fun handleUiWhenSwitching(){
        val fromSelectedItemPosition = binding.fromCurrencyDropdown.selectedItemPosition
        val toSelectedItemPosition = binding.toCurrencyDropdown.selectedItemPosition

        binding.fromCurrencyDropdown.setSelection(toSelectedItemPosition)
        binding.toCurrencyDropdown.setSelection(fromSelectedItemPosition)
    }

    private fun handleUiWhenRetrying(){
        if (!isNetworkAvailable(requireContext())) {
            Toast.makeText(requireContext(), "No Internet Connection", Toast.LENGTH_LONG).show()
        }else {
            binding.errorLayout.root.isVisible = false
            binding.mainLayout.isVisible = true
        }
    }

}