package com.alexmercerind.envirocar.ui

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.alexmercerind.envirocar.R
import com.alexmercerind.envirocar.databinding.ActivityMainBinding
import com.alexmercerind.envirocar.mapper.toSnippet
import com.alexmercerind.envirocar.util.Constants
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.gson.GsonBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.maplibre.android.annotations.MarkerOptions
import org.maplibre.android.annotations.PolylineOptions
import org.maplibre.android.camera.CameraUpdateFactory
import org.maplibre.android.geometry.LatLng
import org.maplibre.android.geometry.LatLngBounds
import org.maplibre.android.maps.MapLibreMap

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()

    private var map: MapLibreMap? = null

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
                                LatLng(
                                    it.geometry.coordinates[1],
                                    it.geometry.coordinates[0]
                                )
                            }

                            // Add polyline.
                            val polyline = PolylineOptions().apply {
                                width(2.0F)
                                color(Color.parseColor("#000000"))
                            }
                            polyline.addAll(points)
                            map.addPolyline(polyline)

                            // Add markers to start & end position.

                            // Start position.
                            map.addMarker(
                                MarkerOptions().apply {
                                    title(getString(R.string.start))
                                    position(points.first())
                                    snippet(result.first().toSnippet())

                                }
                            )

                            // End position.
                            map.addMarker(
                                MarkerOptions().apply {
                                    title(getString(R.string.end))
                                    position(points.last())
                                    snippet(result.last().toSnippet())
                                }
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
