package com.example.data.repository

import com.example.data.mappers.mapToDomain
import com.example.domain.repository.SquareRepository
import com.example.data.remote.SquareRepoService
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class SquareRepositoryImpl(private val squareRepoService: SquareRepoService) : SquareRepository {
    override fun getSquareRepos() = flow {
        emit(Result.success(squareRepoService.getSquareRepos().mapToDomain()))
    }.catch { throwable ->
        emit(Result.failure(throwable))
    }
}
