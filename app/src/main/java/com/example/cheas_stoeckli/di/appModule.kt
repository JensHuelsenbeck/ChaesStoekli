package com.example.cheas_stoeckli.di

import com.example.cheas_stoeckli.data.remote.GoogleDirections.GoogleAPI
import com.example.cheas_stoeckli.data.repositories.Cheese.CheeseAddRepository
import com.example.cheas_stoeckli.data.repositories.Cheese.CheeseRepository
import com.example.cheas_stoeckli.data.repositories.CloudStorageRepository
import com.example.cheas_stoeckli.data.repositories.GoogleRepository
import com.example.cheas_stoeckli.data.repositories.News.NewsAddRepository
import com.example.cheas_stoeckli.data.repositories.News.NewsRepository
import com.example.cheas_stoeckli.data.repositories.Offer.OfferAddRepository
import com.example.cheas_stoeckli.data.repositories.Offer.OfferRepository
import com.example.cheas_stoeckli.data.repositories.Raclette.RacletteAddRepository
import com.example.cheas_stoeckli.data.repositories.Raclette.RacletteRepository
import com.example.cheas_stoeckli.data.repositories.UserPrefRepository
import com.example.cheas_stoeckli.data.repositories.UserRepository
import com.example.cheas_stoeckli.data.repositories.Fondue.FondueAddRepository
import com.example.cheas_stoeckli.data.repositories.Fondue.FondueRepository
import com.example.cheas_stoeckli.data.services.AuthenticationService
import com.example.cheas_stoeckli.domain.domain.usecases.SignOutUseCase
import com.example.cheas_stoeckli.domain.usecases.ObserveCurrentUserUseCase
import com.example.cheas_stoeckli.domain.usecases.SignInWithGoogleUseCase
import com.example.cheas_stoeckli.ui.viewModel.AuthenticationViewModel
import com.example.cheas_stoeckli.ui.viewModel.Cheese.CheeseAddViewModel
import com.example.cheas_stoeckli.ui.viewModel.Cheese.CheeseViewModel
import com.example.cheas_stoeckli.ui.viewModel.NetworkViewModel
import com.example.cheas_stoeckli.ui.viewModel.News.NewsAddViewModel
import com.example.cheas_stoeckli.ui.viewModel.News.NewsDetailViewModel
import com.example.cheas_stoeckli.ui.viewModel.News.NewsViewModel
import com.example.cheas_stoeckli.ui.viewModel.Offer.OfferAddViewModel
import com.example.cheas_stoeckli.ui.viewModel.Offer.OfferViewModel
import com.example.cheas_stoeckli.ui.viewModel.Raclette.RacletteAddViewModel
import com.example.cheas_stoeckli.ui.viewModel.Raclette.RacletteViewModel
import com.example.cheas_stoeckli.ui.viewModel.Fondue.FondueAddViewModel
import com.example.cheas_stoeckli.ui.viewModel.Fondue.FondueViewModel
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
    singleOf(::FondueAddRepository)
    singleOf(::FondueRepository)

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
    viewModelOf(::FondueAddViewModel)
    viewModelOf(::FondueViewModel)

    //Retrofitservice
    single { GoogleAPI.retrofitService }
}

