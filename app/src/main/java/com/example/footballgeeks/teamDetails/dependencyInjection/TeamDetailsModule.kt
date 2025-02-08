package com.example.footballgeeks.teamDetails.dependencyInjection

import com.example.footballgeeks.teamDetails.data.TeamDetailsRepository
import com.example.footballgeeks.teamDetails.data.TeamDetailsRepositoryInterface
import com.example.footballgeeks.teamDetails.data.remote.TeamDetailsDataSource
import com.example.footballgeeks.teamDetails.data.remote.TeamDetailsRemoteDataSource
import com.example.footballgeeks.teamDetails.data.remote.TeamDetailsService
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
class TeamDetailsModule {

    @Provides
    fun providesTeamDetailsService(retrofit: Retrofit): TeamDetailsService {
        return retrofit.create(TeamDetailsService::class.java)
    }

}

@Module
@InstallIn(ViewModelComponent::class)
interface TeamDetailsModuleBinding {

    @Binds
    fun bindTeamDetailsRepository(impl: TeamDetailsRepository): TeamDetailsRepositoryInterface

    @Binds
    fun bindTeamDetailsRemoteDataSource(impl: TeamDetailsRemoteDataSource): TeamDetailsDataSource
}