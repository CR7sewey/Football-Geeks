package com.example.footballgeeks.landingPage.dependencyInjection

import com.example.footballgeeks.landingPage.data.LandingPageRepository
import com.example.footballgeeks.landingPage.data.LandingRepository
import com.example.footballgeeks.landingPage.data.remote.LandingPageRemoteDataSource
import com.example.footballgeeks.landingPage.data.remote.LandingPageService
import com.example.footballgeeks.landingPage.data.remote.RemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class LandingPageModule {

    @Provides
    fun providesLandingPageService(retrofit: Retrofit): LandingPageService {
        return retrofit.create(LandingPageService::class.java)
    }
}

@Module
@InstallIn(ViewModelComponent::class)
interface LandingPageModuleBinding {

    @Binds
    fun bindLandingPageRepository(impl: LandingPageRepository): LandingRepository

    @Binds
    fun bindRemoteDataSource(impl: LandingPageRemoteDataSource): RemoteDataSource

}