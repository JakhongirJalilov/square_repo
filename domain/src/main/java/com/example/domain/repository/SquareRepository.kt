package com.example.domain.repository

import com.example.domain.entity.SquareRepo
import kotlinx.coroutines.flow.Flow

interface SquareRepository {
    fun getSquareRepos(): Flow<Result<List<SquareRepo>>>
}
