package com.example.data.remote

import com.example.data.remote.dto.response.SquareRepoItemResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

// Service class that communicates with the GitHub API to fetch repositories for the "square" organization
class SquareRepoService(val httpClient: HttpClient) {
    suspend fun getSquareRepos() = httpClient.get(SQUARE_REPO_URL).body<List<SquareRepoItemResponse>>()
}

const val SQUARE_REPO_URL = "https://api.github.com/orgs/square/repos"
