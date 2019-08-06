package dev.oscar.ruiz.cvapp.fetchcv.data.model.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Education(
    @Json(name = "degree")
    val degree: String = "",
    @Json(name = "degreeName")
    val degreeName: String = "",
    @Json(name = "location")
    val location: Location = Location(),
    @Json(name = "period")
    val period: String = "",
    @Json(name = "universityName")
    val universityName: String = ""
)