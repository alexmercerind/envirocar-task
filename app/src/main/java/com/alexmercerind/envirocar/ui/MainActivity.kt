package com.alexmercerind.envirocar.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alexmercerind.envirocar.R
import com.alexmercerind.envirocar.repository.EnviroCarRepository
import com.alexmercerind.envirocar.util.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var repository: EnviroCarRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch(Dispatchers.IO) {
            repository.track(
                Constants.USERNAME,
                Constants.TRACK_ID,
                Constants.USERNAME,
                Constants.TOKEN
            )
        }
    }
}