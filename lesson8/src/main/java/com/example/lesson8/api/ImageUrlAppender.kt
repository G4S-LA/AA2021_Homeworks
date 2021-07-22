package com.example.lesson8.api

import com.example.lesson8.api.RetrofitInstance.api
import com.example.lesson8.data.response.ImageResponse

private const val DEFAULT_SIZE = "original"
private const val IDENTIFIER_SMALL_SIZE = "w185"
private const val IDENTIFIER_MEDIUM_SIZE = "w500"
private const val IDENTIFIER_LARGE_SIZE = "w780"

class ImageUrlAppender {

    private var imageResponse: ImageResponse? = null
    private var baseUrl: String = ""
    private var posterSize: String = ""
    private var backdropSize: String = ""
    private var profileSize: String = ""

    enum class Size {
        POSTER,
        BACKDROP,
        PROFILE
    }

    suspend fun getImageUrl(path: String, size: Size): String? {
        loadConfiguration()

        return when (size) {
            Size.POSTER -> buildUrl(baseUrl, posterSize, path)
            Size.BACKDROP -> buildUrl(baseUrl, backdropSize, path)
            else -> buildUrl(baseUrl, profileSize, path)
        }
    }

    private suspend fun loadConfiguration() {
        if (imageResponse != null) return

        imageResponse = api.getConfiguration().images
        baseUrl = imageResponse?.secureBaseUrl.toString()
        posterSize = imageResponse?.posterSizes?.find { size -> size == IDENTIFIER_MEDIUM_SIZE }
            ?: DEFAULT_SIZE
        backdropSize = imageResponse?.backdropSizes?.find { size -> size == IDENTIFIER_LARGE_SIZE }
            ?: DEFAULT_SIZE
        profileSize = imageResponse?.profileSizes?.find { size -> size == IDENTIFIER_SMALL_SIZE }
            ?: DEFAULT_SIZE
    }

    private fun buildUrl(url: String, size: String, path: String) =
        if (url.isEmpty() || path.isEmpty()) null
        else "$url$size$path"

}