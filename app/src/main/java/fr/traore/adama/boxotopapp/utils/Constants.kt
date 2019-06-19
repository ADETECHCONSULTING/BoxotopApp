package fr.traore.adama.boxotopapp.utils

import fr.traore.adama.boxotopapp.BuildConfig


class Constants {

    companion object {
        const val BASE_URL: String = "https://api.themoviedb.org/3/"
        const val BASE_IMAGE_URL: String = "https://image.tmdb.org/t/p/w500/"
        const val API_KEY: String = BuildConfig.ApiKey
    }
}