package com.alexmercerind.envirocar.mapper

import com.alexmercerind.envirocar.data.remote.envirocar.dto.Phenomenons

fun Phenomenons.toSnippet() = """
    CO2 Emission (GPS-based): ${cO2EmissionGPSBased.value.round(2)} ${cO2EmissionGPSBased.unit}
    Consumption (GPS-based): ${consumptionGPSBased.value.round(2)} ${consumptionGPSBased.unit}
    GPS Accuracy: ${gPSAccuracy.value.round(2)} ${gPSAccuracy.unit}
    GPS Altitude: ${gPSAltitude.value.round(2)} ${gPSAltitude.unit}
    GPS Bearing: ${gPSBearing.value.round(2)} ${gPSBearing.unit}
    GPS Speed: ${gPSSpeed.value.round(2)} ${gPSSpeed.unit}
    Maximum GPS Acceleration: ${maximumGPSAcceleration.value.round(2)} ${maximumGPSAcceleration.unit}
    Minimum GPS Acceleration: ${minimumGPSAcceleration.value.round(2)} ${minimumGPSAcceleration.unit}
""".trimIndent()
