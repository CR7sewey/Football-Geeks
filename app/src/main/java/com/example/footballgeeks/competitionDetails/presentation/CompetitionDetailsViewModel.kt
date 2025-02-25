package com.example.footballgeeks.competitionDetails.presentation

import android.accounts.NetworkErrorException
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.footballgeeks.FootballGeeksApp
import com.example.footballgeeks.common.remote.model.CompetitionDetails
import com.example.footballgeeks.common.remote.model.CompetitionsDetailsDTO
import com.example.footballgeeks.common.remote.model.CompetitionsDetailsStandings
import com.example.footballgeeks.common.remote.model.Match
import com.example.footballgeeks.common.remote.model.Season
import com.example.footballgeeks.common.remote.model.StatsPlayerDTO
import com.example.footballgeeks.competitionDetails.data.CompetitionDetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompetitionDetailsViewModel @Inject constructor(private val competitionDetailsRepository: CompetitionDetailsRepository): ViewModel() {

    private val _uiCompetition = MutableStateFlow<CompetitionsDetailsDTO?>(null)
    val uiCompetition: StateFlow<CompetitionsDetailsDTO?> = _uiCompetition

    private val _uiCompetitionStandings = MutableStateFlow<CompetitionsDetailsStandings?>(null)
    val uiCompetitionStandings: StateFlow<CompetitionsDetailsStandings?> = _uiCompetitionStandings

    private val _uiCompetitionScorers = MutableStateFlow<StatsPlayerDTO?>(null)
    val uiCompetitionScorers: StateFlow<StatsPlayerDTO?> = _uiCompetitionScorers

    private val _seasonUsed = MutableStateFlow<String>("")
    val seasonUsed: StateFlow<String> = _seasonUsed

    private val _uiErrorMessage = MutableStateFlow<String>("")
    val uiErrorMessage: StateFlow<String> = _uiErrorMessage

    /*companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {

            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                // Get the Application object from extras
                val application: FootballGeeksApp = checkNotNull(extras[APPLICATION_KEY]) as FootballGeeksApp
                // Create a SavedStateHandle for this ViewModel from extras
                //val savedStateHandle = extras.createSavedStateHandle()
                return CompetitionDetailsViewModel(application.competitionDetailsRepository) as T
            }

        }
    }*/

    fun cleanCodeId() {
        viewModelScope.launch {
            delay(1000)
            _uiCompetition.value = null
            _uiCompetitionStandings.value = null
            _uiCompetitionScorers.value = null
            _uiErrorMessage.value = ""
        }
    }

    fun cleanId() {
        viewModelScope.launch {
            delay(1000)
            _uiCompetitionStandings.value = null
            _uiErrorMessage.value = ""
        }
    }

    fun fetchDataCompetition(code: String) {
        viewModelScope.launch(Dispatchers.IO) {
            var competition: CompetitionsDetailsDTO? = null
            var response = competitionDetailsRepository.getCompetitionDetails(code)
            if (response.isSuccess) {
                Log.d("AQUI 2", response.getOrNull().toString())
                competition = response.getOrNull()
                var conversion: CompetitionsDetailsDTO? = null
                /*if (competition != null && competition.seasons.winner == null) {
                    competition.seasons.winner = ""
                }*/
                _uiCompetition.value = competition
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
            }
        }
    }

    fun fetchDataCompetitionStandings(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            var competition: CompetitionsDetailsStandings? = null
            var response = competitionDetailsRepository.getCompetitionStandings(id)
            if (response.isSuccess) {
                Log.d("AQUI 2", response.getOrNull().toString())
                competition = response.getOrNull()
                var conversion: CompetitionsDetailsStandings? = null
                /*if (competition != null && competition.seasons.winner == null) {
                    competition.seasons.winner = ""
                }*/
                _uiCompetitionStandings.value = competition
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
            }
        }
    }

    fun fetchDataCompetitionScorers(id: String, season: String = "") {
        viewModelScope.launch(Dispatchers.IO) {
            var scorers: StatsPlayerDTO? = null
            Log.d("TEST 1", season)
            var response = competitionDetailsRepository.getCompetitionScorers(id, season)
            if (response.isSuccess) {
                Log.d("AQUI 2", response.getOrNull().toString())
                scorers = response.getOrNull()
                var conversion: CompetitionsDetailsStandings? = null
                /*if (competition != null && competition.seasons.winner == null) {
                    competition.seasons.winner = ""
                }*/
                _uiCompetitionScorers.value = scorers
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
            }
        }
    }

    fun setSeason(value: String) {
        _seasonUsed.value = value
    }


}