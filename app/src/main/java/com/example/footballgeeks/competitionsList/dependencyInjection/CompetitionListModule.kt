package com.example.footballgeeks.competitionsList.dependencyInjection

import com.example.footballgeeks.competitionsList.data.CompetitionListRepositoryInterface
import com.example.footballgeeks.competitionsList.data.CompetitionsListRepository
import com.example.footballgeeks.competitionsList.data.remote.CompetitionsListRemoteDataSource
import com.example.footballgeeks.competitionsList.data.remote.CompetitionsListService
import com.example.footballgeeks.competitionsList.data.remote.RemoteDataSource
import com.example.footballgeeks.teamsList.data.TeamListRepositoryInterface
import com.example.footballgeeks.teamsList.data.TeamsListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class CompetitionListModule {

    @Provides
    fun providesCompetitionListService(retrofit: Retrofit): CompetitionsListService {
        return retrofit.create(CompetitionsListService::class.java)
    }

}

@Module
@InstallIn(ViewModelComponent::class)
interface CompetitionListModuleBinding {

    @Binds
    fun bindCompetitionListRepository(impl: CompetitionsListRepository): CompetitionListRepositoryInterface

    @Binds
    fun bindCompetitionListRemoteDataSource(impl: CompetitionsListRemoteDataSource): RemoteDataSource
}