package com.example.presentation.currencyRates

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.common.Constants
import com.example.common.utils.NetworkUtils
import com.example.common.utils.viewBinding
import com.example.presentation.R
import com.example.presentation.currencyRates.adapters.HistoricalDataAdapter
import com.example.presentation.currencyRates.adapters.LatestRatesAdapter
import com.example.presentation.databinding.FragmentCurrencyRatesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class CurrencyRatesFragment(): Fragment(R.layout.fragment_currency_rates) {

    private val binding by viewBinding(FragmentCurrencyRatesBinding::bind)

    private val viewModel: CurrencyRatesViewModel by viewModels()

    lateinit var  fromCurrency: String

    lateinit var  toCurrency: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fromCurrency = arguments?.getString("fromCurrency").toString()
        toCurrency = arguments?.getString("toCurrency").toString()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvTitle.text = "$fromCurrency to $toCurrency"

        binding.errorLayout.btnRetry.setOnClickListener{
            handleUiWhenRetrying()
        }

        prepareView()
        initObservers()


    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()
        fireEndpoints()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun fireEndpoints(){
        if(fromCurrency.isNotEmpty() && toCurrency.isNotEmpty()){
            val today = LocalDate.now()
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            for (i in 0 until 3) {
                val date = formatter.format(today.minusDays(i.toLong()))
                viewModel.getHistoricalData(date, fromCurrency, toCurrency, Constants.format)
            }

            val popularCurrencies = "$toCurrency,XCD,XDR,XOF,XPF,YER,ZAR,ZMK,ZMW"
            viewModel.getLatestRates(fromCurrency, popularCurrencies, Constants.format)
        }
    }

    private fun prepareView(){
        binding.rvHistoricalData.adapter = HistoricalDataAdapter()
        binding.rvLatestRates.adapter = LatestRatesAdapter()
    }

    private fun initObservers(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.historicalData.collect { result ->
                    if(result != null && result.success) {
                        binding.mainLayout.isVisible = true
                        result.rates
                    }else{
                        binding.errorLayout.root.isVisible = true
                        binding.mainLayout.isVisible = false
                    }
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.latestRates.collect { result ->
                    if(result != null && result.success) {
                        binding.mainLayout.isVisible = true
                        result.rates
                    }else{
                        binding.errorLayout.root.isVisible = true
                        binding.mainLayout.isVisible = false
                    }
                }
            }
        }
    }

    private fun handleUiWhenRetrying(){
        if (!NetworkUtils.isNetworkAvailable(requireContext())) {
            Toast.makeText(requireContext(), "No Internet Connection", Toast.LENGTH_LONG).show()
        }else {
            binding.errorLayout.root.isVisible = false
            binding.mainLayout.isVisible = true
        }
    }
}