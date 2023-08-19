package com.example.presentation.currencyRates

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.base.BaseActivity
import com.example.presentation.R
import com.example.presentation.databinding.ActivityCurrencyConversionBinding
import com.example.presentation.databinding.ActivityCurrencyRatesBinding

class CurrencyRatesActivity() :BaseActivity<ActivityCurrencyRatesBinding>() {

    override val bindLayout: (LayoutInflater) -> ActivityCurrencyRatesBinding
        get() = ActivityCurrencyRatesBinding::inflate

    override fun prepareView() {
        TODO("Not yet implemented")
    }

}