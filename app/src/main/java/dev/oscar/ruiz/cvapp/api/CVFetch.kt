package dev.oscar.ruiz.cvapp.api

import dev.oscar.ruiz.cvapp.fetchcv.data.model.response.CvFetchResponse
import io.reactivex.Single
import retrofit2.http.POST
import retrofit2.http.Path

interface CVFetch {

    /**
     * Fetch http://device-config.feeln.com/android/config-1.5.json file
     */
    @POST("https://device-config.feeln.com/android/config-1.5.json")
    fun fetchCv(
        @Path("id") id: String
    ): Single<CvFetchResponse>
}