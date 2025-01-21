package com.example.footballgeeks.gameDetails.presentation

import android.accounts.NetworkErrorException
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.footballgeeks.FootballGeeksApp
import com.example.footballgeeks.common.remote.model.Match
import com.example.footballgeeks.gameDetails.data.MatchDetailsRepository
import com.example.footballgeeks.landingPage.presentation.ui.MatchesListUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MatchDetailsViewModel(private val matchDetailsRepository: MatchDetailsRepository ): ViewModel() {

    private val _uiCurrentGame = MutableStateFlow<Match?>(null)
    val uiCurrentGame: StateFlow<Match?> = _uiCurrentGame

    private val _uiErrorMessage = MutableStateFlow<String>("")
    val uiErrorMessage: StateFlow<String> = _uiErrorMessage


    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {

            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                // Get the Application object from extras
                val application: FootballGeeksApp = checkNotNull(extras[APPLICATION_KEY]) as FootballGeeksApp
                // Create a SavedStateHandle for this ViewModel from extras
                //val savedStateHandle = extras.createSavedStateHandle()
                return MatchDetailsViewModel(application.matchDetailsRepository) as T
            }

        }
    }

    fun cleanId() {
        viewModelScope.launch {
            delay(1000)
            _uiCurrentGame.value = null
            _uiErrorMessage.value = ""
        }
    }

    fun fetchData(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            var currentGame: Match? = null
            var response = matchDetailsRepository.getMatchDetails(id)
            if (response.isSuccess) {
                Log.d("AQUI 2", response.getOrNull().toString())
                currentGame = response.getOrNull()
                var conversion: Match? = null
                /*if (currentGame) {
                    conversion = Match(
                        area = currentGame?.area ?: ,
                        competition = currentGame.competition,
                        id = currentGame.id,
                        status = currentGame.status,
                        minute = (currentGame.minute ?: 0).toString(),
                        homeTeam = currentGame.homeTeam,
                        awayTeam = currentGame.awayTeam,
                        score = currentGame.score
                    )
                }*/
                // Log.d("AQUI", currentGames.toString())
                //_uiCurrentGames.value = MatchesListUiState(list = c, isLoading = false)
                _uiCurrentGame.value = currentGame
            }
            else {
                val ex = response.exceptionOrNull()
                Log.d("AQUI 2",ex?.message.toString())
                if (ex is NetworkErrorException) {
                    _uiErrorMessage.value = "No internet connection..."
                }
                else {
                    _uiErrorMessage.value = "Something went wrong..."

                }
                }                }
        }

}