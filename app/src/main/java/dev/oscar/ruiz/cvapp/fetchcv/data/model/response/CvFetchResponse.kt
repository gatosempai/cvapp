package dev.oscar.ruiz.cvapp.fetchcv.data.model.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import dev.oscar.ruiz.cvapp.utils.Status

@JsonClass(generateAdapter = true)
data class CvFetchResponse(
    @Json(name = "control")
    val control: Control = Control(),
    @Json(name = "education")
    val education: List<Education> = listOf(),
    @Json(name = "personalInformation")
    val personalInformation: PersonalInformation = PersonalInformation(),
    @Json(name = "professionalInformation")
    val professionalInformation: ProfessionalInformation = ProfessionalInformation(),
    val apiError: ApiError = ApiError(),
    var status: Status = Status.ERROR
)