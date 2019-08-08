package dev.oscar.ruiz.cvapp.fetchcv.data.model.response


import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Class for ProfessionalInformation data model
 */

@JsonClass(generateAdapter = true)
data class ProfessionalInformation(
    @Json(name = "history")
    val history: List<History> = listOf(),
    @Json(name = "summary")
    val summary: String = "",
    @Json(name = "title")
    val title: String = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.createTypedArrayList(History),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(history)
        parcel.writeString(summary)
        parcel.writeString(title)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProfessionalInformation> {
        override fun createFromParcel(parcel: Parcel): ProfessionalInformation {
            return ProfessionalInformation(parcel)
        }

        override fun newArray(size: Int): Array<ProfessionalInformation?> {
            return arrayOfNulls(size)
        }
    }
}