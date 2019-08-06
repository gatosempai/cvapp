package dev.oscar.ruiz.cvapp.fetchcv.data.model.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class History(
    @Json(name = "companyName")
    val companyName: String = "",
    @Json(name = "location")
    val location: Location = Location(),
    @Json(name = "period")
    val period: String = "",
    @Json(name = "positionHeld")
    val positionHeld: List<String> = listOf()
)