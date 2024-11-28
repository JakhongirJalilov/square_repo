package com.example.presentation.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.repository.SquareRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SquareRepoViewModel(
    private val squareRepository: SquareRepository
) : ViewModel() {

    // UI State to hold the data and loading status
    private val _uiState = MutableStateFlow(SquareRepoState())
    val uiState = _uiState.asStateFlow()

    // Initialization block: Automatically fetch the Square repos when the ViewModel is created
    init {
        onFetchSquareRepos()
    }

    // Fetch the Square repos from the repository
    fun onFetchSquareRepos() {
        viewModelScope.launch {
            squareRepository.getSquareRepos().onStart {
                _uiState.update { state -> state.copy(isLoading = true, isError = false) }
            }.onCompletion {
                _uiState.update { state -> state.copy(isLoading = false) }
            }.collect { result ->
                result.onSuccess { repos ->
                    _uiState.update { state -> state.copy(squareRepos = repos) }
                }
                result.onFailure {
                    _uiState.update { state -> state.copy(isError = true) }
                }
            }
        }
    }
}
