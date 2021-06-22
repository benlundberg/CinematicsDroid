package com.example.cinematics.utils
//
//import android.app.Application
//import androidx.room.Room
//import com.example.cinematics.data.configs.ServiceConfig
//import com.example.cinematics.data.dao.AppDatabase
//import com.example.cinematics.data.dao.MoviesDao
//import com.example.cinematics.data.remotes.MovieDetailsRemoteService
//import com.example.cinematics.data.remotes.MovieRemoteService
//import com.example.cinematics.data.remotes.SearchRemoteService
//import com.example.cinematics.data.services.MovieDetailsService
//import com.example.cinematics.data.services.MoviesService
//import com.example.cinematics.data.services.SearchService
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//
//class AppContainer {
//
//    // Dao
//    private val moviesDao: MoviesDao = database.moviesDao()
//
//    // Remote services
//    private val movieRemoteService: MovieRemoteService =
//        retrofit.create(MovieRemoteService::class.java)
//    private val searchRemoteService: SearchRemoteService =
//        retrofit.create(SearchRemoteService::class.java)
//    private val movieDetailsRemoteService: MovieDetailsRemoteService =
//        retrofit.create(MovieDetailsRemoteService::class.java)
//
//    // Services
//    private val movieService: MoviesService =
//        MoviesService(movieRemoteService)
//    private val searchService: SearchService =
//        SearchService(searchRemoteService)
//    private val movieDetailsService: MovieDetailsService =
//        MovieDetailsService(movieDetailsRemoteService, moviesDao)
//
//    // ViewModels
//    val moviesViewModelFactory: MoviesViewModelFactory =
//        MoviesViewModelFactory(movieService)
//    val searchViewModelFactory: SearchViewModelFactory =
//        SearchViewModelFactory(searchService)
//    val favouritesViewModelFactory: FavouritesViewModelFactory =
//        FavouritesViewModelFactory(moviesDao)
//    val movieDetailsViewModelFactory: MovieDetailsViewModelFactory =
//        MovieDetailsViewModelFactory(movieDetailsService, moviesDao)
//
//    private val retrofit: Retrofit get() = Retrofit.Builder()
//        .baseUrl(ServiceConfig.BaseUrl)
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//
////    private val database: AppDatabase get() =
//}