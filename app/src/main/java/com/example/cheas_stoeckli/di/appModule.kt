package com.example.cheas_stoeckli.di

import com.example.cheas_stoeckli.data.remote.GoogleDirections.GoogleAPI
import com.example.cheas_stoeckli.data.repositories.CheeseAddRepository
import com.example.cheas_stoeckli.data.repositories.CheeseRepository
import com.example.cheas_stoeckli.data.repositories.CloudStorageRepository
import com.example.cheas_stoeckli.data.repositories.GoogleRepository
import com.example.cheas_stoeckli.data.repositories.NewsAddRepository
import com.example.cheas_stoeckli.data.repositories.NewsRepository
import com.example.cheas_stoeckli.data.repositories.OfferAddRepository
import com.example.cheas_stoeckli.data.repositories.OfferRepository
import com.example.cheas_stoeckli.data.repositories.RacletteAddRepository
import com.example.cheas_stoeckli.data.repositories.RacletteRepository
import com.example.cheas_stoeckli.data.repositories.UserPrefRepository
import com.example.cheas_stoeckli.data.repositories.UserRepository
import com.example.cheas_stoeckli.data.services.AuthenticationService
import com.example.cheas_stoeckli.domain.domain.usecases.SignOutUseCase
import com.example.cheas_stoeckli.domain.usecases.ObserveCurrentUserUseCase
import com.example.cheas_stoeckli.domain.usecases.SignInWithGoogleUseCase
import com.example.cheas_stoeckli.ui.viewModel.AuthenticationViewModel
import com.example.cheas_stoeckli.ui.viewModel.CheeseAddViewModel
import com.example.cheas_stoeckli.ui.viewModel.CheeseViewModel
import com.example.cheas_stoeckli.ui.viewModel.NetworkViewModel
import com.example.cheas_stoeckli.ui.viewModel.NewsAddViewModel
import com.example.cheas_stoeckli.ui.viewModel.NewsDetailViewModel
import com.example.cheas_stoeckli.ui.viewModel.NewsViewModel
import com.example.cheas_stoeckli.ui.viewModel.OfferAddViewModel
import com.example.cheas_stoeckli.ui.viewModel.OfferViewModel
import com.example.cheas_stoeckli.ui.viewModel.RacletteAddViewModel
import com.example.cheas_stoeckli.ui.viewModel.RacletteViewModel
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
    singleOf(::UserPrefRepository)
    singleOf(::GoogleRepository)
    singleOf(::OfferAddRepository)
    singleOf(::OfferRepository)
    singleOf(::CheeseAddRepository)
    singleOf(::CheeseRepository)
    singleOf(::RacletteAddRepository)
    singleOf(::RacletteRepository)

    // UseCases
    singleOf(::SignInWithGoogleUseCase)
    singleOf(::SignOutUseCase)
    singleOf(::ObserveCurrentUserUseCase)

    // ViewModels
    viewModelOf(::AuthenticationViewModel)
    viewModelOf(::NewsViewModel)
    viewModelOf(::NewsAddViewModel)
    viewModelOf(::NetworkViewModel)
    viewModelOf(::NewsDetailViewModel)
    viewModelOf(::OfferViewModel)
    viewModelOf(::OfferAddViewModel)
    viewModelOf(::CheeseViewModel)
    viewModelOf(::CheeseAddViewModel)
    viewModelOf(::RacletteViewModel)
    viewModelOf(::RacletteAddViewModel)

    //Retrofitservice
    single { GoogleAPI.retrofitService }
}

