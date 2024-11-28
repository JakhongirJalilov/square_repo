package com.example.poqtask.di

import com.example.data.repository.SquareRepositoryImpl
import com.example.domain.repository.SquareRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::SquareRepositoryImpl) { bind<SquareRepository>() }
}
