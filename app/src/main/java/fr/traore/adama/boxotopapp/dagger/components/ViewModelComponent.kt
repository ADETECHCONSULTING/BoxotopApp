package fr.traore.adama.boxotopapp.dagger.components

import dagger.Component
import fr.traore.adama.boxotopapp.dagger.modules.NetworkModule
import fr.traore.adama.boxotopapp.viewmodel.DetailViewModel
import fr.traore.adama.boxotopapp.viewmodel.ExploreViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelComponent{

    /**
     * Injects required dependencies into the specified ExploreViewModel.
     * @param exploreViewModel ExploreViewModel in which to inject the dependencies
     */
    fun inject(exploreViewModel: ExploreViewModel)
    fun inject(detailViewModel: DetailViewModel)


    @Component.Builder
    interface Builder{

        fun build() : ViewModelComponent

        fun networkModule(networkModule: NetworkModule) : Builder
    }
}