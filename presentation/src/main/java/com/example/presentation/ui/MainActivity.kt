package com.example.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.poqtask.presentation.theme.PoqTaskTheme
import com.example.presentation.ui.main.SquareRepoScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PoqTaskTheme {
                SquareRepoScreen()
            }
        }
    }
}
