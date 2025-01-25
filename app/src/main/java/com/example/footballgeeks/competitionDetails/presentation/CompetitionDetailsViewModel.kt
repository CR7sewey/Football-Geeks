package com.example.footballgeeks.competitionDetails.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.footballgeeks.FootballGeeksApp
import com.example.footballgeeks.competitionDetails.data.CompetitionDetailsRepository
import com.example.footballgeeks.gameDetails.presentation.MatchDetailsViewModel

class CompetitionDetailsViewModel(private val competitionDetailsRepository: CompetitionDetailsRepository): ViewModel() {
    companion object {
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
    }

}