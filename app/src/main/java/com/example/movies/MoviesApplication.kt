package com.example.movies

import android.app.Application
import com.example.movies.data.AppContainer
import com.example.movies.data.DefaultAppContainer


class MoviesApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}