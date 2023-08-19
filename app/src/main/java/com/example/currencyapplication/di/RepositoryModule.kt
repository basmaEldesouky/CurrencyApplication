package com.example.currencyapplication.di

import com.example.common.Mapper
import com.example.data.remote.RemoteDataSourceImp
import com.example.data.remote.RemoteServices
import com.example.data.repo.CurrencyRepositoryImp
import com.example.domain.repo.CurrencyRepositoryContract
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRemoteDataSource(
        apiService: RemoteServices,
    ) = RemoteDataSourceImp(
        apiService
    )

    @Singleton
    @Provides
    fun provideCurrencyRepository(remoteDataSource: RemoteDataSourceImp): CurrencyRepositoryContract =
        CurrencyRepositoryImp(remoteDataSource)
}