package com.example.domain.entity

data class HistoricalData(
    val base: String,
    val date: String,
    val historical: Boolean,
    val rates: RatesX,
    val success: Boolean,
    val timestamp: Int
)