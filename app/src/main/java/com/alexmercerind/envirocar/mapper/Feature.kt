package com.alexmercerind.envirocar.mapper

import com.alexmercerind.envirocar.data.remote.envirocar.dto.Feature

fun Feature.toSnippet() = """
    ${geometry.coordinates[1]}, ${geometry.coordinates[0]}
    ${properties.time}
    ${properties.phenomenons.toSnippet()}
""".trimIndent()
