package com.example.footballgeeks.landingPage.presentation

import android.accounts.NetworkErrorException
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.footballgeeks.FootballGeeksApp
import com.example.footballgeeks.common.remote.model.Match
import com.example.footballgeeks.common.remote.model.MatchesDTO
import com.example.footballgeeks.landingPage.data.remote.LandingPageService
import com.example.mycinema.common.data.remote.RetroFitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import com.example.footballgeeks.landingPage.presentation.ui.MatchesListUiState


class LandingPageViewModel(private val landingPageService: LandingPageService): ViewModel() {

    private val _uiCurrentGames = MutableStateFlow<MatchesListUiState>(MatchesListUiState())
    val uiCurrentGames: StateFlow<MatchesListUiState> = _uiCurrentGames

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
                return LandingPageViewModel(application.landingPageService) as T
            }

        }
    }

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                var currentGames: List<Match> = emptyList()
                var response = landingPageService.getMatches()
                _uiCurrentGames.value = MatchesListUiState(isLoading = true)
                if (response.isSuccessful) {
                    Log.d("AQUI 2", response.body()?.matches.toString())
                    currentGames = response.body()?.matches ?: emptyList<Match>()
                    val conversion = currentGames.map { value -> Match(
                        area = value.area,
                        competition= value.competition,
                        id = value.id,
                        status = value.status,
                        minute = (value.minute ?: 0).toString(),
                        homeTeam = value.homeTeam,
                        awayTeam = value.awayTeam,
                        score = value.score) }
                   // Log.d("AQUI", currentGames.toString())
                    _uiCurrentGames.value = MatchesListUiState(list = conversion, isLoading = false)
                }
                else {
                    _uiCurrentGames.value = MatchesListUiState(isLoading = false, isError = true, errorMessage = "No internet connection..." )
                }
            }
            catch (ex: Exception) {
                ex.printStackTrace()
                Log.d("AQUI 2",ex.message.toString())
                if (ex is NetworkErrorException) {
                    _uiCurrentGames.value = MatchesListUiState(isLoading = false, isError = true, errorMessage = "No internet connection..." )
                }
                else {
                    _uiCurrentGames.value = MatchesListUiState(isLoading = false, isError = true, errorMessage = "Something went wrong..." )
                }
             }
            }

    }

}