package dev.oscar.ruiz.cvapp.fetchcv.data.model.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Control(
    @Json(name = "islookingJob")
    val islookingJob: Boolean = false,
    @Json(name = "lastUpdate")
    val lastUpdate: String = ""
)