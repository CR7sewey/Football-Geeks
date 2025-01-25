package com.example.footballgeeks.competitionsList.presentation

import android.accounts.NetworkErrorException
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.footballgeeks.FootballGeeksApp
import com.example.footballgeeks.common.remote.model.CompetitionDetails
import com.example.footballgeeks.competitionsList.data.CompetitionsListRepository
import com.example.footballgeeks.competitionsList.presentation.ui.CompetitionsListUIState
import com.example.footballgeeks.landingPage.data.remote.LandingPageService
import com.example.mycinema.common.data.remote.RetroFitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.Int

class CompetitionsListViewModel(private val competitionsListRepository: CompetitionsListRepository): ViewModel() {
    private val _uiCompetitions = MutableStateFlow<CompetitionsListUIState>(CompetitionsListUIState())
    val uiCompetitions: StateFlow<CompetitionsListUIState> = _uiCompetitions

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
                return CompetitionsListViewModel(application.competitionsListRepository) as T
            }

        }
    }

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            var competitions: List<CompetitionDetails> = emptyList()
            var response = competitionsListRepository.getCompetitions()
            _uiCompetitions.value = CompetitionsListUIState(isLoading = true)
            if (response.isSuccess) {
                Log.d("AQUI 2", response.getOrNull().toString())
                competitions = response.getOrNull() ?: emptyList<CompetitionDetails>()
                val conversion = competitions.map { value -> CompetitionDetails(
                    id = value.id,
                    name = value.name,
                    area = value.area,
                    code = value.code,
                    type = value.type,
                    emblem = value.emblem,
                    currentSeason = value.currentSeason
                )
                }
                // Log.d("AQUI", currentGames.toString())
                _uiCompetitions.value = CompetitionsListUIState(list = conversion, isLoading = false)
            }
            else {
                val ex = response.exceptionOrNull()
                Log.d("AQUI 2",ex?.message.toString())
                if (ex is NetworkErrorException) {
                    _uiCompetitions.value = CompetitionsListUIState(isLoading = false, isError = true, errorMessage = "No internet connection..." )
                }
                else {
                    _uiCompetitions.value = CompetitionsListUIState(isLoading = false, isError = true, errorMessage = "Something went wrong..." )
                }                }
        }

    }
}