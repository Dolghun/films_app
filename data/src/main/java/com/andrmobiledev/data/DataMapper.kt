package com.andrmobiledev.data

import com.andrmobiledev.api.responses.ShowImageResponse
import com.andrmobiledev.api.responses.ShowsResponse
import com.andrmobiledev.domain.entities.Show
import com.andrmobiledev.domain.entities.ShowImage

fun ShowsResponse.toDomain() =
    with(this) {
        Show(id,
            name,
            summary,
            rating.average,
            image?.let { mapImage(it) },
            officialSite,
            premiered)
    }

private fun mapImage(image: ShowImageResponse) = with(image) { ShowImage(medium, original) }