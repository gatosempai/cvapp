package dev.oscar.ruiz.cvapp.fetchcv.data.model.response


import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Class for Education data model
 */

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
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(Location::class.java.classLoader),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(degree)
        parcel.writeString(degreeName)
        parcel.writeParcelable(location, flags)
        parcel.writeString(period)
        parcel.writeString(universityName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Education> {
        override fun createFromParcel(parcel: Parcel): Education {
            return Education(parcel)
        }

        override fun newArray(size: Int): Array<Education?> {
            return arrayOfNulls(size)
        }
    }
}