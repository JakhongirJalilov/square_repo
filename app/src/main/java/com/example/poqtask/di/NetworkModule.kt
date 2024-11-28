package com.example.poqtask.di

import com.example.data.remote.SquareRepoService
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {
    single<HttpClient> {
        HttpClient(Android) {
            install(Logging)
            install(WebSockets)
            install(ContentNegotiation) {
                json(
                    json = Json {
                        ignoreUnknownKeys = true
                        prettyPrint = true
                        isLenient = true
                        encodeDefaults = true
                    }
                )
            }
        }
    }

    single {
        SquareRepoService(httpClient = get())
    }
}
