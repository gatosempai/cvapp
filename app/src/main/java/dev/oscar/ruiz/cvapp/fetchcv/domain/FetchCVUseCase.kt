package dev.oscar.ruiz.cvapp.fetchcv.domain

import dev.oscar.ruiz.cvapp.di.scope.AppScope
import dev.oscar.ruiz.cvapp.fetchcv.data.FetchRepository
import javax.inject.Inject

@AppScope
class FetchCVUseCase
@Inject constructor(
    private val fetchRepository: FetchRepository
) {
    /**
     * Fetch the config-1.5.json file
     */
    /*fun fetchCvs(observer: Observer<CvFetchResponse>): Disposable {
        return fetchRepository    .getDeviceConfig().subscribeWith(observer) as Disposable
    }*/
}