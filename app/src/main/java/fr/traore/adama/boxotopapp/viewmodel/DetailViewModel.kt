package fr.traore.adama.boxotopapp.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import fr.traore.adama.boxotopapp.R
import fr.traore.adama.boxotopapp.model.MovieItem
import fr.traore.adama.boxotopapp.utils.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailViewModel(val movieId:Int?) : BaseViewModel() {
    private val TAG: String = DetailViewModel::class.java.simpleName
    val errorClickListener = View.OnClickListener { loadMovieDetails() }
    private val movieTitle = MutableLiveData<String>()
    private val movieOverview = MutableLiveData<String>()
    private val movieImageUrl = MutableLiveData<String>()
    private val movieRealaseDate = MutableLiveData<String>()
    private val movieRating = MutableLiveData<Double>()


    init {
        loadMovieDetails()
    }

    private fun loadMovieDetails(){
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
        movieTitle.value = movieItem.title
        movieImageUrl.value = Constants.BASE_IMAGE_URL+ movieItem.poster_path
        movieOverview.value = movieItem.overview
        movieRating.value = movieItem.vote_average
        movieRealaseDate.value = movieItem.release_date
    }

    private fun onRetrieveMoviesListError(error: String){
        errorMessage.value = R.string.movie_detail_fetch_error
        Log.d(TAG, error)
    }

    override fun onCleared() {
        super.onCleared()

        if(!subscription.isDisposed)
            subscription.dispose()
    }

    fun getImageUrl() : MutableLiveData<String> {
        return movieImageUrl
    }

    fun getTitle() : MutableLiveData<String> {
        return movieTitle
    }

    fun getOverview() : MutableLiveData<String> {
        return movieOverview
    }

    fun getReleaseDate() : MutableLiveData<String> {
        return movieRealaseDate
    }

    fun getRating() : MutableLiveData<Double> {
        return movieRating
    }


}