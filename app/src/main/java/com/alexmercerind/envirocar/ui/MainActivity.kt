package com.alexmercerind.envirocar.ui

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.alexmercerind.envirocar.R
import com.alexmercerind.envirocar.databinding.ActivityMainBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.gson.GsonBuilder
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

        val map = SupportMapFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .add(R.id.mapView, map)
            .commit()

        lifecycleScope.launch {
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
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.points.filterNotNull().collect { result ->
                    withContext(Dispatchers.Main) {

                        map.getMapAsync { map ->
                            map.setMinZoomPreference(1.0F)
                            map.setMaxZoomPreference(20.0F)

                            val latLngBoundsBuilder = LatLngBounds.builder()

                            val points = result.map {
                                LatLng(
                                    it.geometry.coordinates[1],
                                    it.geometry.coordinates[0]
                                ).also { latLng ->
                                    latLngBoundsBuilder.include(latLng)
                                }
                            }

                            map.addPolyline(
                                PolylineOptions()
                                    .addAll(points)
                                    .width(4.0F)
                                    .color(Color.parseColor("#000000"))
                            )

                            // Add markers to start & end position.

                            // Start position.
                            map.addMarker(
                                MarkerOptions()
                                    .position(points.first())
                                    .contentDescription(getString(R.string.start))
                            )

                            // End position.
                            map.addMarker(
                                MarkerOptions()
                                    .position(points.last())
                                    .contentDescription(getString(R.string.end))
                            )

                            // Move camera to show available points.

                            map.moveCamera(
                                CameraUpdateFactory.newLatLngBounds(
                                    latLngBoundsBuilder.build(),
                                    80
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}
