package com.example.presentation.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.domain.entity.SquareRepo
import com.example.poqtask.presentation.theme.PoqTaskTheme
import com.example.presentation.R
import org.koin.compose.koinInject

@Composable
fun SquareRepoScreen(viewModel: SquareRepoViewModel = koinInject<SquareRepoViewModel>()) {

    // Collect the UI state from the ViewModel
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    // Main content of the screen
    SquareRepoContent(uiState = uiState, onRetry = { viewModel.onFetchSquareRepos() })
}

// A composable to display the list of repositories or a loading spinner
@Composable
private fun SquareRepoContent(
    uiState: SquareRepoState,
    onRetry: () -> Unit
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Gray)
                .padding(innerPadding)
        ) {
            when {
                uiState.isLoading -> {
                    // Show loading indicator when data is loading
                    LoadingIndicator(modifier = Modifier.align(Alignment.Center))
                }

                uiState.isError -> {
                    // Show error message and retry button
                    ErrorContent(
                        modifier = Modifier.align(Alignment.Center),
                        onRetry = onRetry
                    )
                }

                uiState.squareRepos.isEmpty() -> {
                    // Show "No content" message if repositories are empty
                    NoContentMessage(modifier = Modifier.align(Alignment.Center))
                }


                else -> {
                    // Show the list of repositories
                    RepoList(uiState.squareRepos)
                }
            }
        }
    }
}

// Composable to display a loading indicator
@Composable
private fun LoadingIndicator(modifier: Modifier) {
    CircularProgressIndicator(modifier = modifier)
}

// Composable to show a "No Content" message
@Composable
private fun NoContentMessage(modifier: Modifier) {
    Text(
        text = stringResource(R.string.content_empty),
        color = Color.Black,
        style = MaterialTheme.typography.bodyMedium,
        modifier = modifier
    )
}

// Error content message
@Composable
private fun ErrorContent(modifier: Modifier, onRetry: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.error_message),
            color = Color.Red,
            style = MaterialTheme.typography.bodyMedium
        )
        Button(
            onClick = onRetry,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = stringResource(R.string.retry))
        }
    }
}

// Composable to show the list of repositories
@Composable
private fun RepoList(repos: List<SquareRepo>) {
    LazyColumn {
        items(repos) { repo ->
            RepoItem(repo = repo)
        }
    }
}

// Reusable Composable for each repository item
@Composable
private fun RepoItem(repo: SquareRepo) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Text(
            text = repo.name,
            color = Color.Black,
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = repo.description,
            color = Color.Black,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}


@Preview
@Composable
fun SquareReposScreenPreview() {
    PoqTaskTheme {
        SquareRepoContent(uiState = SquareRepoState(), onRetry = {})
    }
}
