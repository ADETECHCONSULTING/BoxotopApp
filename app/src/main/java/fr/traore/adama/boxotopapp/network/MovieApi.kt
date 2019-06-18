package fr.traore.adama.boxotopapp.network

import fr.traore.adama.boxotopapp.model.MovieItem
import fr.traore.adama.boxotopapp.model.MovieResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi{

    @GET("/movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey:String,
        @Query("language") language: String,
        @Query("page") page:Int
    ) : Observable<MovieResponse>


    @GET("/movie/{movie_id}")
    fun getMovieDetail(
        @Path("movie_id") id:Int,
        @Query("api_key") apiKey:String,
        @Query("language") language: String
    ) : Observable<MovieItem>
}