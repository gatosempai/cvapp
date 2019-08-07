package dev.oscar.ruiz.cvapp.di.module

import dagger.Module
import dagger.Provides
import dev.oscar.ruiz.cvapp.BuildConfig
import dev.oscar.ruiz.cvapp.api.CVFetch
import dev.oscar.ruiz.cvapp.di.scope.AppScope
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Module to be used by Dagger to provide SvodFacade instances
 */

@Module
class ApiModule {

    /**
     * Provides the CVFetch implementation
     * @param retrofit The retrofit object used to instantiate the service
     * @return The Post Service implementation
     */
    @Provides
    @AppScope
    fun provideApi(): CVFetch {
        val logging = HttpLoggingInterceptor()
        when (BuildConfig.DEBUG) {
            true -> {
                logging.level = HttpLoggingInterceptor.Level.BODY
            }
            else -> {
                logging.level = HttpLoggingInterceptor.Level.BASIC
            }
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_ENDPOINT)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
            .create(CVFetch::class.java)
    }
}