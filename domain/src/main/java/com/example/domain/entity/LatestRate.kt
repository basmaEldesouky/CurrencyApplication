package com.example.domain.entity

data class LatestRate(
    val base: String,
    val date: String,
    val rates: RatesX,
    val success: Boolean,
    val timestamp: Int
)