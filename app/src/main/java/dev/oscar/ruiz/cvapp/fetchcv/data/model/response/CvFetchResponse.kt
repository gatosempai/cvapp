package dev.oscar.ruiz.cvapp.fetchcv.data.model.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import dev.oscar.ruiz.cvapp.utils.Status

@JsonClass(generateAdapter = true)
data class CvFetchResponse(
    @Json(name = "cvDataList")
    val cvDataList: List<CvData> = listOf(),
    val apiError: ApiError = ApiError(),
    var status: Status = Status.ERROR
)