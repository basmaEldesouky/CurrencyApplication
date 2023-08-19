package com.example.data.remote

import com.example.domain.entity.HistoricalData
import com.example.domain.entity.LatestRate
import com.example.domain.entity.Symbols
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RemoteServices {
    @GET("/{date}")
    suspend fun getHistoricalData(@Path("date") date: String,
                                  @Query("access_key") access_key: String,
                                  @Query("base") base: String,
                                  @Query("symbols") symbols: String,
                                  @Query("format") format: Int): HistoricalData

    @GET("/latest")
    suspend fun getLatestRates(@Query("access_key") access_key: String,
                               @Query("base") base: String,
                               @Query("symbols") symbols: String,
                               @Query("format") format: Int): LatestRate
}