package cz.radovanholik.moviesviewer.di

import cz.radovanholik.moviesviewer.ui.detail.DetailViewModel
import cz.radovanholik.moviesviewer.ui.list.MoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { DetailViewModel() }
    viewModel { MoviesViewModel() }
}