package com.example.footballgeeks.gameDetails.dependencyInjection

import com.example.footballgeeks.gameDetails.data.MatchDetailsRepository
import com.example.footballgeeks.gameDetails.data.MatchDetailsRepositoryInterface
import com.example.footballgeeks.gameDetails.data.remote.MatchDetailsDataSource
import com.example.footballgeeks.gameDetails.data.remote.MatchDetailsRemoteDataSource
import com.example.footballgeeks.gameDetails.data.remote.MatchDetailsService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class MatchDetailsModule {

    @Provides
    fun providesMatchDetailsService(retrofit: Retrofit): MatchDetailsService {
        return retrofit.create(MatchDetailsService::class.java)
    }

}

@Module
@InstallIn(ViewModelComponent::class)
interface MatchDetailsModuleBinding {

    @Binds
    fun bindMatchDetailsRepository(impl: MatchDetailsRepository): MatchDetailsRepositoryInterface

    @Binds
    fun bindMatchDetailsRemoteDataSource(impl: MatchDetailsRemoteDataSource): MatchDetailsDataSource
}