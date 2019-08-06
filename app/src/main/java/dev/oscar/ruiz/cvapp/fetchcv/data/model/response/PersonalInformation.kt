package dev.oscar.ruiz.cvapp.fetchcv.data.model.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PersonalInformation(
    @Json(name = "age")
    val age: Int = 0,
    @Json(name = "country")
    val country: String = "",
    @Json(name = "email")
    val email: String = "",
    @Json(name = "lastName")
    val lastName: String = "",
    @Json(name = "name")
    val name: String = "",
    @Json(name = "phone")
    val phone: String = ""
)