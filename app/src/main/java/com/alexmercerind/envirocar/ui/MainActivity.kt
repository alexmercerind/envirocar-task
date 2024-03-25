package com.alexmercerind.envirocar.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.alexmercerind.envirocar.R
import com.alexmercerind.envirocar.databinding.ActivityMainBinding
import com.alexmercerind.envirocar.util.Constants
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.gson.GsonBuilder
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.geometry.LatLngBounds
import com.mapbox.mapboxsdk.plugins.annotation.LineManager
import com.mapbox.mapboxsdk.plugins.annotation.LineOptions
import com.mapbox.mapboxsdk.plugins.annotation.SymbolManager
import com.mapbox.mapboxsdk.plugins.annotation.SymbolOptions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()

    companion object {
        const val MARKER = "marker"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch(Dispatchers.IO) {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.details.filterNotNull().collect { result ->
                    withContext(Dispatchers.Main) {
                        binding.materialToolbar.title = result.description
                        binding.materialToolbar.subtitle = result.name
                        binding.materialToolbar.setOnMenuItemClickListener {
                            MaterialAlertDialogBuilder(this@MainActivity)
                                .setTitle(R.string.details)
                                .setMessage(
                                    GsonBuilder().setPrettyPrinting().create().toJson(result)
                                )
                                .create()
                                .show()
                            false
                        }

                        binding.progressBar.visibility = View.GONE
                        binding.mapView.visibility = View.VISIBLE
                    }
                }
            }
        }
        lifecycleScope.launch(Dispatchers.IO) {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.points.filterNotNull().collect { result ->
                    withContext(Dispatchers.Main) {

                        binding.mapView.getMapAsync { map ->
                            map.setStyle(Constants.MAP_STYLE)
                            map.setMinZoomPreference(1.0)
                            map.setMaxZoomPreference(20.0)

                            val points = result.map {
                                LatLng(it.geometry.coordinates[1], it.geometry.coordinates[0])
                            }

                            map.getStyle { style ->

                                style.addImage(
                                    MARKER,
                                    AppCompatResources.getDrawable(
                                        this@MainActivity,
                                        R.mipmap.ic_symbol_icon
                                    )!!
                                )

                                val lineManager = LineManager(binding.mapView, map, style)
                                val symbolManager = SymbolManager(binding.mapView, map, style)

                                lineManager.create(
                                    LineOptions()
                                        .withLatLngs(points)
                                        .withLineWidth(2.0F)
                                        .withLineColor("#000000")
                                )

                                // Add markers to start & end position.

                                // Start position.
                                symbolManager.create(
                                    SymbolOptions()
                                        .withIconImage(MARKER)
                                        .withIconSize(1.5F)
                                        .withLatLng(points.first())
                                        .withTextAnchor(getString(R.string.start))
                                )

                                // End position.
                                symbolManager.create(
                                    SymbolOptions()
                                        .withIconImage(MARKER)
                                        .withIconSize(1.5F)
                                        .withLatLng(points.last())
                                        .withTextAnchor(getString(R.string.end))
                                )

                                // Move camera to show available points.
                                val bounds = LatLngBounds.fromLatLngs(points)
                                map.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 80))
                            }


                        }
                    }
                }
            }
        }
    }
}
