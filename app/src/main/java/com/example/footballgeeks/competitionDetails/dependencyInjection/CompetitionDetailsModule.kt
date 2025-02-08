package com.example.footballgeeks.competitionDetails.dependencyInjection

import com.example.footballgeeks.competitionDetails.data.CompetitionDetailsRepository
import com.example.footballgeeks.competitionDetails.data.CompetitionDetailsRepositoryInterface
import com.example.footballgeeks.competitionDetails.data.remote.CompetitionDetailsDataSource
import com.example.footballgeeks.competitionDetails.data.remote.CompetitionDetailsRemoteDataSource
import com.example.footballgeeks.competitionDetails.data.remote.CompetitionDetailsService
import com.example.footballgeeks.competitionsList.data.CompetitionListRepositoryInterface
import com.example.footballgeeks.competitionsList.data.CompetitionsListRepository
import com.example.footballgeeks.competitionsList.data.remote.CompetitionsListRemoteDataSource
import com.example.footballgeeks.competitionsList.data.remote.CompetitionsListService
import com.example.footballgeeks.competitionsList.data.remote.RemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class CompetitionDetailsModule {

    @Provides
    fun providesCompetitionDetailsService(retrofit: Retrofit): CompetitionDetailsService {
        return retrofit.create(CompetitionDetailsService::class.java)
    }

}

@Module
@InstallIn(ViewModelComponent::class)
interface CompetitionDetailsModuleBinding {

    @Binds
    fun bindCompetitionDetailsRepository(impl: CompetitionDetailsRepository): CompetitionDetailsRepositoryInterface

    @Binds
    fun bindCompetitionDetailsRemoteDataSource(impl: CompetitionDetailsRemoteDataSource): CompetitionDetailsDataSource
}