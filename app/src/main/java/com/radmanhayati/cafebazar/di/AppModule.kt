package com.radmanhayati.cafebazar.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.radmanhayati.cafebazar.data.local.MovieDatabase
import com.radmanhayati.cafebazar.data.local.MovieEntity
import com.radmanhayati.cafebazar.data.remote.MovieApi
import com.radmanhayati.cafebazar.data.remote.MovieRemoteMediator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMovieDatabase(@ApplicationContext context: Context): MovieDatabase {
        return Room.databaseBuilder(
            context,
            MovieDatabase::class.java,
            "movies.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BASIC
        return OkHttpClient
            .Builder()
            .addInterceptor(logger)
            .addInterceptor { interceptor ->
                val requestBuilder = interceptor.request()
                    .newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5NTAwMTRlMGZkMjA2NjJjZDdjZjc5YzBjMTg0OTJkMiIsInN1YiI6IjYyYjlkZTNlM2RkMTI2MDA4YzcyMTdiZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.sitTMdi-Dzuim0bpAfBMLc5ToWDWE3nsb25irgMYb-Y")
                val request = requestBuilder
                    .build()
                interceptor.proceed(request = request)
            }
            .build()
    }


    @Provides
    @Singleton
    fun provideMovieApi(okHttpClient: OkHttpClient): MovieApi {
        return Retrofit.Builder()
            .baseUrl(MovieApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideMoviePager(movieDb: MovieDatabase, movieApi: MovieApi): Pager<Int, MovieEntity> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = MovieRemoteMediator(
                movieDb = movieDb,
                movieApi = movieApi
            ),
            pagingSourceFactory = {
                movieDb.dao.pagingSource()
            }
        )
    }
}