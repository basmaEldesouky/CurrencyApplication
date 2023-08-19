package com.example.presentation.currencyConversion

import android.view.LayoutInflater
import com.example.base.BaseActivity
import com.example.presentation.databinding.ActivityCurrencyConversionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrencyConversionActivity(): BaseActivity<ActivityCurrencyConversionBinding>() {

    override val bindLayout: (LayoutInflater) -> ActivityCurrencyConversionBinding
        get() = ActivityCurrencyConversionBinding::inflate

    override fun prepareView() {

    }


}