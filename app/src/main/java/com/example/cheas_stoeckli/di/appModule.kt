package com.example.cheas_stoeckli.di

import com.example.cheas_stoeckli.data.remote.GoogleDirections.GoogleAPI
import com.example.cheas_stoeckli.data.repositories.CloudStorageRepository
import com.example.cheas_stoeckli.data.repositories.NewsAddRepository
import com.example.cheas_stoeckli.data.repositories.NewsRepository
import com.example.cheas_stoeckli.data.repositories.UserRepository
import com.example.cheas_stoeckli.data.services.AuthenticationService
import com.example.cheas_stoeckli.domain.domain.usecases.SignOutUseCase
import com.example.cheas_stoeckli.domain.usecases.ObserveCurrentUserUseCase
import com.example.cheas_stoeckli.domain.usecases.SignInWithGoogleUseCase
import com.example.cheas_stoeckli.ui.viewModel.AuthenticationViewModel
import com.example.cheas_stoeckli.ui.viewModel.NetworkViewModel
import com.example.cheas_stoeckli.ui.viewModel.NewsAddViewModel
import com.example.cheas_stoeckli.ui.viewModel.NewsViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


val appModule = module {
    // Services
    singleOf(::AuthenticationService)

    // Repositories
    singleOf(::UserRepository)
    singleOf(::NewsRepository)
    singleOf(::NewsAddRepository)
    singleOf(::CloudStorageRepository)

    // UseCases
    singleOf(::SignInWithGoogleUseCase)
    singleOf(::SignOutUseCase)
    singleOf(::ObserveCurrentUserUseCase)

    // ViewModels
    viewModelOf(::AuthenticationViewModel)
    viewModelOf(::NewsViewModel)
    viewModelOf(::NewsAddViewModel)
    viewModelOf(::NetworkViewModel)

    //Retrofitservice

    single { GoogleAPI.retrofitService }
}

