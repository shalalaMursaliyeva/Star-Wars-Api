package com.example.starwars

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class StarWarsApplication: Application() {
    override fun onCreate() {
        super.onCreate()

    }
}