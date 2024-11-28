package com.example.data.mappers

import com.example.domain.entity.SquareRepo
import com.example.data.remote.dto.response.SquareRepoItemResponse

// Extension function to map a list of SquareRepoItemResponse to a list of SquareRepo
fun List<SquareRepoItemResponse>.mapToDomain(): List<SquareRepo> {
    return map { repo ->
        SquareRepo(
            name = repo.name.orEmpty(),
            description = repo.description.orEmpty()
        )
    }
}
