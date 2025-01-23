package com.example.footballgeeks.teamsList.presentation

import android.accounts.NetworkErrorException
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.footballgeeks.FootballGeeksApp
import com.example.footballgeeks.common.remote.model.Match
import com.example.footballgeeks.common.remote.model.Team
import com.example.footballgeeks.common.remote.model.TeamDetails
import com.example.footballgeeks.landingPage.data.remote.LandingPageService
import com.example.footballgeeks.landingPage.presentation.LandingPageViewModel
import com.example.footballgeeks.landingPage.presentation.ui.MatchesListUiState
import com.example.footballgeeks.teamsList.data.TeamsListRepository
import com.example.footballgeeks.teamsList.presentation.ui.TeamsListUIState
import com.example.mycinema.common.data.remote.RetroFitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.Int

class TeamsListViewModel(private val teamsListRepository: TeamsListRepository): ViewModel() {

    private val _uiTeams = MutableStateFlow<TeamsListUIState>(TeamsListUIState())
    val uiTeams: StateFlow<TeamsListUIState> = _uiTeams

    private val _uiErrorMessage = MutableStateFlow<String>("")
    val uiErrorMessage: StateFlow<String> = _uiErrorMessage

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {

            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                // Get the Application object from extras
                val apiService = RetroFitClient.retrofit.create(LandingPageService::class.java)
                val application: FootballGeeksApp = checkNotNull(extras[APPLICATION_KEY]) as FootballGeeksApp
                //val repository = application.repository
                // Create a SavedStateHandle for this ViewModel from extras
                //val savedStateHandle = extras.createSavedStateHandle()
                return TeamsListViewModel(application.teamsListRepository) as T
            }

        }
    }

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            var currentTeams: List<TeamDetails> = emptyList()
            var response = teamsListRepository.getTeams()
            _uiTeams.value = TeamsListUIState(isLoading = true)
            if (response.isSuccess) {
                Log.d("AQUI 2", response.getOrNull().toString())
                currentTeams = response.getOrNull() ?: emptyList<TeamDetails>()
                val conversion = currentTeams.map { value -> TeamDetails(
                     id = value.id,
                     name = value.name,
                     shortName = value.shortName ?: "",
                     tla = value.tla ?: "",
                     crest = value.crest ?: "",
                     address = value.address ?: "",
                     website = value.website ?: "",
                     founded = value.founded,
                     venue = value.venue ?: "",
                    lastUpdated = value.lastUpdated ?: ""
                )
                }
                // Log.d("AQUI", currentGames.toString())
                _uiTeams.value = TeamsListUIState(list = conversion, isLoading = false)
            }
            else {
                val ex = response.exceptionOrNull()
                Log.d("AQUI 2",ex?.message.toString())
                if (ex is NetworkErrorException) {
                    _uiTeams.value = TeamsListUIState(isLoading = false, isError = true, errorMessage = "No internet connection..." )
                }
                else {
                    _uiTeams.value = TeamsListUIState(isLoading = false, isError = true, errorMessage = "Something went wrong..." )
                }                }
        }

    }

}