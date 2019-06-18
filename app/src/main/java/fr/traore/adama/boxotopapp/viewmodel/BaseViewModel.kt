package fr.traore.adama.boxotopapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.traore.adama.boxotopapp.dagger.components.DaggerViewModelComponent
import fr.traore.adama.boxotopapp.dagger.components.ViewModelComponent
import fr.traore.adama.boxotopapp.dagger.modules.NetworkModule
import fr.traore.adama.boxotopapp.network.MovieApi
import io.reactivex.disposables.Disposable
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {

    @Inject
    lateinit var movieApi: MovieApi

    protected lateinit var subscription: Disposable

    val errorMessage: MutableLiveData<Int> = MutableLiveData()

    private val viewModelComponent: ViewModelComponent = DaggerViewModelComponent
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }


    private fun inject(){
        when(this){
            is ExploreViewModel -> {
                viewModelComponent.inject(this)
            }
            is DetailViewModel -> {
                viewModelComponent.inject(this)
            }
        }
    }
}