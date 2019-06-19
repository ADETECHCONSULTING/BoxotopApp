package fr.traore.adama.boxotopapp.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import fr.traore.adama.boxotopapp.R
import fr.traore.adama.boxotopapp.model.MovieResponse
import fr.traore.adama.boxotopapp.ui.adapter.MovieListAdapter
import fr.traore.adama.boxotopapp.utils.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class ExploreViewModel : BaseViewModel() {
    private val TAG: String = ExploreViewModel::class.java.simpleName
    private var myMovieListAdapter = MovieListAdapter()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadMovies() }


    init {
        loadMovies()
    }


    private fun loadMovies(){
        subscription = movieApi.getPopularMovies(Constants.API_KEY, "fr", 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveMoviesListStart() }
            .doOnTerminate { onRetrieveMoviesListFinish() }
            .subscribe(
                {result -> onRetrieveMoviesListSuccess(result)},
                {error -> onRetrieveMoviesListError(error.localizedMessage)}
            )
    }

    private fun onRetrieveMoviesListStart(){
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveMoviesListFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveMoviesListSuccess(movieResponse : MovieResponse){
        if(movieResponse.results != null)
            myMovieListAdapter.resetData(movieResponse.results)
    }

    private fun onRetrieveMoviesListError(error: String){
        errorMessage.value = R.string.movies_fetch_error
        Log.d(TAG, error)
    }

    override fun onCleared() {
        super.onCleared()

        if(!subscription.isDisposed)
            subscription.dispose()
    }

    fun getMovieListAdapter() : MovieListAdapter{
        return myMovieListAdapter
    }

}