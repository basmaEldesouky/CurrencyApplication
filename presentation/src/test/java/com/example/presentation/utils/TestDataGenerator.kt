package com.example.presentation.utils

import com.example.domain.entity.HistoricalData
import com.example.domain.entity.LatestRate
import com.example.domain.entity.RatesX

class TestDataGenerator {
    companion object {
        const val fromAmount = "1"
        const val date = "2023-08-18"
        const val apiKey = "123456789123456789123456789123456789"
        const val base = "EUR"
        const val currencyList = "USD,EGP"
        const val popularCurrencies = "USD,EGP,XCD,XDR,XOF,XPF,YER,ZAR,ZMK,ZMW,ZWL"
        const val format = 1

        fun generateHistoricalData(): HistoricalData {
            return HistoricalData(
                base = "EUR",
                date = "2013-03-16",
                historical = true,
                rates = RatesX(null, null, null, null, null, null, null, null, null, null,
                    null, null, null, null, null, null, null, null, null, null, null, null,
                    null, null, null, null, null, null, null, null, null, null, null, null,
                    null, null, null, null, null, null, null, null, null, 31.259128, null, null,
                    31.259128, null, null, null, null, null, null, null, null, null, null, null,
                    null, null, null, null, null, null, null, null, null, null, null, null,
                    null, null, null, null, null, null, null, null, null, null, null, null,
                    null, null, null, null, null, null, null, null, null, null, null, null,
                    null, null, null, null, null, null, null, null, null, null, null, null,
                    null, null, null, null, null, null, null, null, null, null, null, null,
                    null, null, null, null, null, null, null, null, null, null, null, null,
                    null, null, null, null, null, null, null, null, null, null, null, null,
                    null, null, null, null, null, null, null, null, null, 1.307716, null, null,
                    null, null, null, null, null, null, null, null, null, null, null, null,
                    null, null, null, null, null),
                success = true,
                timestamp = 1363478399
            )
        }

        fun generateLatestRates(): LatestRate {
            return LatestRate(
                base = "EUR",
                date = "2013-03-16",
                rates = RatesX(null, null, null, null, null, null, null, null, null, null,
                    null, null, null, null, null, null, null, null, null, null, null, null,
                    null, null, null, null, null, null, null, null, null, null, null, null,
                    null, null, null, null, null, null, null, null, null, 31.259128, null, null,
                    null, null, null, null, null, null, null, null, null, null, null, null,
                    null, null, null, null, null, null, null, null, null, null, null, null,
                    null, null, null, null, null, null, null, null, null, null, null, null,
                    null, null, null, null, null, null, null, null, null, null, null, null,
                    null, null, null, null, null, null, null, null, null, null, null, null,
                    null, null, null, null, null, null, null, null, null, null, null, null,
                    null, null, null, null, null, null, null, null, null, null, null, null,
                    null, null, null, null, null, null, null, null, null, null, null, null,
                    null, null, null, null, null, null, null, null, null, 1.307716, null, null,
                    null, null, null, null, null, null, null, null, 31.259128, 31.259128, 31.259128, 31.259128,
                    31.259128, 31.259128, 31.259128, 31.259128, null),
                success = true,
                timestamp = 1363478399
            )
        }
    }
}