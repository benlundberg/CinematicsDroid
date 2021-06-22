package com.example.cinematics.di

import android.content.Context
import androidx.room.Room
import com.example.cinematics.data.configs.ServiceConfig
import com.example.cinematics.data.dao.AppDatabase
import com.example.cinematics.data.dao.MoviesDao
import com.example.cinematics.data.remotes.MovieDetailsRemoteService
import com.example.cinematics.data.remotes.MovieRemoteService
import com.example.cinematics.data.remotes.SearchRemoteService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(ServiceConfig.BaseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideMoviesDo(db: AppDatabase) = db.moviesDao()

    @Provides
    fun provideMovieRemoteService(retrofit: Retrofit): MovieRemoteService = retrofit.create(MovieRemoteService::class.java)

    @Provides
    fun provideSearchRemoteService(retrofit: Retrofit): SearchRemoteService = retrofit.create(SearchRemoteService::class.java)

    @Provides
    fun provideMovieDetailsRemoteService(retrofit: Retrofit): MovieDetailsRemoteService = retrofit.create(MovieDetailsRemoteService::class.java)

}