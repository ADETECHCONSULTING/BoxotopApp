package fr.traore.adama.boxotopapp.model
import com.squareup.moshi.Json


data class MovieResponse(
    @Json(name = "page")
    val page: Int,
    @Json(name = "results")
    val results: List<MovieItem>?,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults: Int
)

data class MovieItem(
    @Json(name = "backdrop_path")
    val backdrop_path: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "overview")
    val overview: String,
    @Json(name = "popularity")
    val popularity: Double,
    @Json(name = "poster_path")
    val poster_path: String,
    @Json(name = "release_date")
    val release_date: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "video")
    val video: Boolean,
    @Json(name = "vote_average")
    val vote_average: Double
)