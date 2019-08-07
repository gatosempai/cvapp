package dev.oscar.ruiz.cvapp.fetchcv.data

import dev.oscar.ruiz.cvapp.api.CVFetch
import dev.oscar.ruiz.cvapp.di.scope.AppScope
import dev.oscar.ruiz.cvapp.fetchcv.data.model.response.CvFetchResponse
import dev.oscar.ruiz.cvapp.utils.Status
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@AppScope
class FetchRepository
@Inject constructor(
    private val service: CVFetch
) {

    private val times = 3L

    fun fetchCv(): Single<CvFetchResponse> {
        return service.fetchCv()
            .subscribeOn(Schedulers.io())
            .retry(times)
            .doOnSuccess {
                it.status = Status.SUCCESS
            }
        /*.onErrorReturn {
            CvFetchResponse(
                status = Status.ERROR,
                apiError = ApiError(it.message)
            )
        }*/
    }
}