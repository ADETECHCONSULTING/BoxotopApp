package fr.traore.adama.boxotopapp.viewmodel

import androidx.lifecycle.ViewModel
import fr.traore.adama.boxotopapp.dagger.components.DaggerViewModelComponent
import fr.traore.adama.boxotopapp.dagger.components.ViewModelComponent
import fr.traore.adama.boxotopapp.dagger.modules.NetworkModule

abstract class BaseViewModel : ViewModel() {

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
        }
    }
}