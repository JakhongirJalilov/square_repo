package com.example.poqtask.di

import com.example.presentation.ui.main.SquareRepoViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::SquareRepoViewModel)
}
