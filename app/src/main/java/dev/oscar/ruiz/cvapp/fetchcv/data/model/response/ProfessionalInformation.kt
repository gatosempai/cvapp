package dev.oscar.ruiz.cvapp.fetchcv.data.model.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProfessionalInformation(
    @Json(name = "history")
    val history: List<History> = listOf(),
    @Json(name = "summary")
    val summary: String = "",
    @Json(name = "title")
    val title: String = ""
)