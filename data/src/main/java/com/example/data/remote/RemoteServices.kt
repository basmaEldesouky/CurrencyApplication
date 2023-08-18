package com.example.data.remote

import com.example.domain.entity.HistoricalData
import com.example.domain.entity.LatestRate
import com.example.domain.entity.Symbols
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RemoteServices {

    @GET("symbols")
    suspend fun getSymbols(@Query("access_key") access_key: Long): Symbols

    @GET("{date}")
    suspend fun getHistoricalData(@Path("date") date: String,
                                  @Query("access_key") access_key: Long,
                                  @Query("base") base: String,
                                  @Query("symbols") symbols: List<String>): HistoricalData

    @GET("latest")
    suspend fun getLatestRates(@Query("access_key") access_key: Long,
                               @Query("base") base: String,
                               @Query("symbols") symbols: List<String>): LatestRate
}