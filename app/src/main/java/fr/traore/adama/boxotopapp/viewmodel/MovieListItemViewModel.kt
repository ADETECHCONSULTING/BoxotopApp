package fr.traore.adama.boxotopapp.viewmodel

import androidx.lifecycle.MutableLiveData
import fr.traore.adama.boxotopapp.model.MovieItem
import fr.traore.adama.boxotopapp.utils.Constants

class MovieListItemViewModel: BaseViewModel(){
    private val imageUrl = MutableLiveData<String>()
    private val title = MutableLiveData<String>()
    private val overview = MutableLiveData<String>()
    val movieId = MutableLiveData<Int>()

    fun bind(movie: MovieItem){
        imageUrl.value = Constants.BASE_IMAGE_URL+movie.poster_path
        title.value = movie.title
        overview.value = movie.overview
        movieId.value = movie.id
    }

    fun getImageUrl() : MutableLiveData<String> {
        return imageUrl
    }

    fun getTitle() : MutableLiveData<String> {
        return title
    }

    fun getOverview() : MutableLiveData<String> {
        return overview
    }
}