package dev.oscar.ruiz.cvapp.fetchcv.data.model.response

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Class for CvData data model
 */

@JsonClass(generateAdapter = true)
data class CvData(
    @Json(name = "control")
    val control: Control = Control(),
    @Json(name = "education")
    val education: List<Education> = listOf(),
    @Json(name = "personalInformation")
    val personalInformation: PersonalInformation = PersonalInformation(),
    @Json(name = "professionalInformation")
    val professionalInformation: ProfessionalInformation = ProfessionalInformation()
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(Control::class.java.classLoader),
        parcel.createTypedArrayList(Education),
        parcel.readParcelable(PersonalInformation::class.java.classLoader),
        parcel.readParcelable(ProfessionalInformation::class.java.classLoader)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(control, flags)
        parcel.writeTypedList(education)
        parcel.writeParcelable(personalInformation, flags)
        parcel.writeParcelable(professionalInformation, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CvData> {
        override fun createFromParcel(parcel: Parcel): CvData {
            return CvData(parcel)
        }

        override fun newArray(size: Int): Array<CvData?> {
            return arrayOfNulls(size)
        }
    }
}