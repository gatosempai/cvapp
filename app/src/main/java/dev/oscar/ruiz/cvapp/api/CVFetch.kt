package dev.oscar.ruiz.cvapp.api

import dev.oscar.ruiz.cvapp.BuildConfig
import dev.oscar.ruiz.cvapp.fetchcv.data.model.response.CvFetchResponse
import io.reactivex.Single
import retrofit2.http.GET

interface CVFetch {

    /**
     * Fetch json file hosted on gist
     */
    @GET(BuildConfig.API_ENDPOINT + "/oscar-ruiz-globant/e453b4cd26c759c90a92f38bd1d8161f/raw/68398aa456bfb803c77cd34bdf61e74d42395c99/cv-list.json")
    fun fetchCv(): Single<CvFetchResponse>
}