package com.example.footballgeeks.teamsList.dependencyInjection

import com.example.footballgeeks.teamDetails.data.remote.TeamDetailsRemoteDataSource
import com.example.footballgeeks.teamsList.data.TeamListRepositoryInterface
import com.example.footballgeeks.teamsList.data.TeamsListRepository
import com.example.footballgeeks.teamsList.data.remote.RemoteDataSource
import com.example.footballgeeks.teamsList.data.remote.TeamsListRemoteDataSource
import com.example.footballgeeks.teamsList.data.remote.TeamsListService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class TeamListModule {

    @Provides
    fun providesTeamListService(retrofit: Retrofit): TeamsListService {
        return retrofit.create(TeamsListService::class.java)
    }

}

@Module
@InstallIn(ViewModelComponent::class)
interface TeamListModuleBinding {

    @Binds
    fun bindTeamListRepository(impl: TeamsListRepository): TeamListRepositoryInterface

    @Binds
    fun bindTeamListRemoteDataSource(impl: TeamsListRemoteDataSource): RemoteDataSource
}