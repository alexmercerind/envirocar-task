package com.alexmercerind.envirocar

import android.app.Application
import com.mapbox.mapboxsdk.Mapbox
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class EnviroCar : Application() {
    override fun onCreate() {
        super.onCreate()
        Mapbox.getInstance(this)
    }
}
