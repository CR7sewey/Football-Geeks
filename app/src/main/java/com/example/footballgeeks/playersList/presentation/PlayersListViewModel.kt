package com.example.footballgeeks.playersList.presentation

import android.accounts.NetworkErrorException
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.footballgeeks.FootballGeeksApp
import com.example.footballgeeks.common.remote.model.PersonDTO
import com.example.footballgeeks.playersList.data.PlayersListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PlayersListViewModel(private val playersListRepository: PlayersListRepository): ViewModel() {

    private val _uiPlayers = MutableStateFlow<PersonDTO?>(null)
    val uiPlayers: StateFlow<PersonDTO?> = _uiPlayers

    private val _uiPlayersList = MutableStateFlow<MutableList<PersonDTO?>>(mutableListOf())
    val uiPlayersList: StateFlow<MutableList<PersonDTO?>> = _uiPlayersList

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
                return PlayersListViewModel(application.playersListRepository) as T
            }

        }
    }


    fun fetchAllData(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
                var player: PersonDTO?
                var response = playersListRepository.getPlayer(id)
                if (response.isSuccess) {
                    Log.d("AQUI 3", response.getOrNull().toString())
                    player = response.getOrNull()
                    // Log.d("AQUI", currentGames.toString())
                    //_uiCurrentGames.value = MatchesListUiState(list = c, isLoading = false)
                    _uiPlayers.value = player
                    val players = _uiPlayersList.value
                    players.add(player)
                    _uiPlayersList.value = players

                } else {
                    val ex = response.exceptionOrNull()
                    Log.d("AQUI 2", ex?.message.toString())
                    if (ex is NetworkErrorException) {
                        _uiErrorMessage.value = "No internet connection..."
                    } else {
                        _uiErrorMessage.value = "Something went wrong..."

                    }
                }

        }

    }

    fun fetchUniqueData(id: String = "") {

    }



}