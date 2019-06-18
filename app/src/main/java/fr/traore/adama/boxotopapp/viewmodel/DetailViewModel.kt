package fr.traore.adama.boxotopapp.viewmodel

import android.graphics.Movie
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import fr.traore.adama.boxotopapp.R
import fr.traore.adama.boxotopapp.model.MovieItem
import fr.traore.adama.boxotopapp.utils.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailViewModel(val movieId:Int?) : BaseViewModel() {
    val Tag: String = DetailViewModel::class.java.simpleName;
    val errorClickListener = View.OnClickListener { loadMovieDetails() }
    val currentMovie: MutableLiveData<MovieItem> = MutableLiveData()

    init {
        loadMovieDetails()
    }

    fun loadMovieDetails(){
        if (movieId != null) {
            subscription = movieApi.getMovieDetail(movieId, Constants.API_KEY, "fr")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrieveMoviesListStart() }
                .doOnTerminate { onRetrieveMoviesListFinish() }
                .subscribe(
                    { result -> onRetrieveMoviesListSuccess(result) },
                    { error -> onRetrieveMoviesListError(error.localizedMessage) }
                )
        }else{
            errorMessage.value = R.string.movie_detail_fetch_error
        }
    }

    private fun onRetrieveMoviesListStart(){
        errorMessage.value = null
    }

    private fun onRetrieveMoviesListFinish(){
    }

    private fun onRetrieveMoviesListSuccess(movieItem: MovieItem){
        currentMovie.value = movieItem
    }

    private fun onRetrieveMoviesListError(error: String){
        errorMessage.value = R.string.movie_detail_fetch_error
        Log.d(Tag, error)
    }

    override fun onCleared() {
        super.onCleared()

        if(!subscription.isDisposed)
            subscription.dispose()
    }

    fun getImageUrl() : String? {
        return Constants.BASE_IMAGE_URL+currentMovie.value?.poster_path
    }

    fun getTitle() : String? {
        return currentMovie.value?.title
    }

    fun getOverview() : String? {
        return currentMovie.value?.overview
    }
}