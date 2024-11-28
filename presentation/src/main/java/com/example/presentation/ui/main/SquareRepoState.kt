package com.example.presentation.ui.main

import androidx.compose.runtime.Immutable
import com.example.domain.entity.SquareRepo

@Immutable
data class SquareRepoState(
    val squareRepos: List<SquareRepo> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false
)
