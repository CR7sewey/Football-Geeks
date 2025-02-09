package com.example.landingpage.landingPage.dependencyInjection

import com.example.mycinema.common.data.remote.RetroFitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class FootballGeeksModule {

    @Provides
    fun providesRetrofit(): Retrofit {
        return RetroFitClient.retrofit
    }

    @Provides
    @DispatcherIO
    fun providesDispatcherIO(): CoroutineDispatcher {
        return Dispatchers.IO
    }

}