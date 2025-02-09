package com.example.landingpage.landingPage.presentation

import android.accounts.NetworkErrorException
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.landingpage.landingPage.data.LandingPageRepository
import com.example.landingpage.landingPage.dependencyInjection.DispatcherIO
import com.example.landingpage.landingPage.presentation.ui.MatchesListUiState
import com.example.landingpage.landingPage.remote.model.Match

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class LandingPageViewModel @Inject constructor(private val landingPageRepository: LandingPageRepository, @DispatcherIO private val dispatcher: CoroutineDispatcher = Dispatchers.IO): ViewModel() {

    private val _uiCurrentGames = MutableStateFlow<MatchesListUiState>(MatchesListUiState())
    val uiCurrentGames: StateFlow<MatchesListUiState> = _uiCurrentGames

    private val _uiErrorMessage = MutableStateFlow<String>("")
    val uiErrorMessage: StateFlow<String> = _uiErrorMessage

    /*companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {

            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                // Get the Application object from extras
                val apiService = RetroFitClient.retrofit.create(LandingPageService::class.java)
                val application: FootballGeeksApp = checkNotNull(extras[APPLICATION_KEY]) as FootballGeeksApp
                //val repository = application.repository
                // Create a SavedStateHandle for this ViewModel from extras
                //val savedStateHandle = extras.createSavedStateHandle()
                return LandingPageViewModel(application.landingPageRepository) as T
            }

        }
    }*/

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch(dispatcher) {
                var currentGames: List<Match> = emptyList()
                var response = landingPageRepository.getMatches()
                _uiCurrentGames.value = MatchesListUiState(isLoading = true)
                if (response.isSuccess) {
                    Log.d("AQUI 2", response.getOrNull().toString())
                    currentGames = response.getOrNull() ?: emptyList<Match>()
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
                    val ex = response.exceptionOrNull()
                    Log.d("AQUI 2",ex?.message.toString())
                    if (ex is NetworkErrorException) {
                        _uiCurrentGames.value = MatchesListUiState(isLoading = false, isError = true, errorMessage = "No internet connection..." )
                    }
                    else {
                        _uiCurrentGames.value = MatchesListUiState(isLoading = false, isError = true, errorMessage = "Something went wrong..." )
                    }                }
            }

            }

    }

