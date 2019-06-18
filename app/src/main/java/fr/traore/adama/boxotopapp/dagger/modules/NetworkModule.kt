package fr.traore.adama.boxotopapp.dagger.modules

import dagger.Module
import dagger.Provides
import fr.traore.adama.boxotopapp.network.MovieApi
import fr.traore.adama.boxotopapp.utils.Constants
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
object NetworkModule{

    /**
     * Provides MyWebApi service for dagger uses
     */
    @Provides
    @Singleton
    internal fun provideMyWebApi(retrofit: Retrofit): MovieApi{
        return retrofit.create(MovieApi::class.java)
    }

    /**
     * Provides the logging for retrofit
     */
    @Provides
    @Singleton
    internal fun provideLoggingInterceptor() : OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    /**
     * Provides the retrofit object for dagger uses
     * Docu: Its a call adapter which uses RxJava2 for creating observables.
     *      Adding this class to Retrofit allows you to return an Observable, Flowable, Single, Completable or Maybe from service methods.
     */
    @Provides
    @Singleton
    internal fun provideRetrofitInterface(client: OkHttpClient): Retrofit{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }
}