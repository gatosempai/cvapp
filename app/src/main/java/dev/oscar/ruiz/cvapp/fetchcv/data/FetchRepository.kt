package dev.oscar.ruiz.cvapp.fetchcv.data

import dev.oscar.ruiz.cvapp.api.CVFetch
import dev.oscar.ruiz.cvapp.di.scope.AppScope
import dev.oscar.ruiz.cvapp.fetchcv.data.model.response.CvFetchResponse
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@AppScope
class FetchRepository
@Inject constructor(
    private val service: CVFetch
) {

    fun fetchCv(): Single<CvFetchResponse> {
        return service.fetchCv("oscar-ruiz-globant/e453b4cd26c759c90a92f38bd1d8161f")
            .subscribeOn(Schedulers.io())
            .retry(3L)
            .onErrorReturn {
                CvFetchResponse()
            }
    }
}