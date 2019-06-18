package fr.traore.adama.boxotopapp.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import fr.traore.adama.boxotopapp.R
import fr.traore.adama.boxotopapp.network.MovieApi
import fr.traore.adama.boxotopapp.utils.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ExploreViewModel : BaseViewModel() {
    val Tag: String = ExploreViewModel::class.java.simpleName;

    @Inject
    lateinit var movieApi: MovieApi
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadMovies() }

    private lateinit var subscription: Disposable

    init {
        loadMovies()
    }


    fun loadMovies(){
        subscription = movieApi.getPopularMovies(Constants.API_KEY, "fr", 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveMoviesListStart() }
            .doOnTerminate { onRetrieveMoviesListFinish() }
            .subscribe(
                {onRetrieveMoviesListSuccess()},
                {error -> onRetrieveMoviesListError(error.localizedMessage)}
            )
    }

    private fun onRetrieveMoviesListStart(){
        //loadingVisibility.value = View.VISIBLE
    }

    private fun onRetrieveMoviesListFinish(){
        //loadingVisibility.value = View.GONE
    }

    private fun onRetrieveMoviesListSuccess(){

    }

    private fun onRetrieveMoviesListError(error: String){
        errorMessage.value = R.string.movies_fetch_error

        Log.d(Tag, error)
    }

    override fun onCleared() {
        super.onCleared()

        if(!subscription.isDisposed)
            subscription.dispose()
    }

    fun getLoadingVisibility() : LiveData<Int> {
        return loadingVisibility
    }
}