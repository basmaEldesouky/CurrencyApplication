package com.example.presentation.currencyRates

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.common.utils.viewBinding
import com.example.presentation.R
import com.example.presentation.databinding.FragmentCurrencyRatesBinding
import kotlinx.coroutines.launch

class CurrencyRatesFragment(): Fragment(R.layout.fragment_currency_rates) {

    private val binding by viewBinding(FragmentCurrencyRatesBinding::bind)

    private val viewModel: CurrencyRatesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }

    private fun initObservers(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {

            }
        }
    }

}