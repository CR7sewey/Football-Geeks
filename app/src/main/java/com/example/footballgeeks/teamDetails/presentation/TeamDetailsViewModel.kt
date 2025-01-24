package com.example.footballgeeks.teamDetails.presentation

import android.accounts.NetworkErrorException
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.footballgeeks.FootballGeeksApp
import com.example.footballgeeks.common.remote.model.Match
import com.example.footballgeeks.common.remote.model.TeamDetails
import com.example.footballgeeks.gameDetails.presentation.MatchDetailsViewModel
import com.example.footballgeeks.teamDetails.data.TeamDetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TeamDetailsViewModel(private val teamDetailsRepository: TeamDetailsRepository): ViewModel() {

    private val _uiTeam = MutableStateFlow<TeamDetails?>(null)
    val uiTeam: StateFlow<TeamDetails?> = _uiTeam

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
                return TeamDetailsViewModel(application.teamDetailsRepository) as T
            }

        }
    }

    fun cleanId() {
        viewModelScope.launch {
            delay(1000)
            _uiTeam.value = null
            _uiErrorMessage.value = ""
        }
    }

    fun fetchData(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            var team: TeamDetails? = null
            var response = teamDetailsRepository.getTeamDetails(id)
            if (response.isSuccess) {
                Log.d("AQUI 2", response.getOrNull().toString())
                team = response.getOrNull()

                _uiTeam.value = team
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