package dev.oscar.ruiz.cvapp.fetchcv.data.model.response


import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Class for History data model
 */

@JsonClass(generateAdapter = true)
data class History(
    @Json(name = "companyName")
    val companyName: String = "",
    @Json(name = "location")
    val location: Location = Location(),
    @Json(name = "period")
    val period: String = "",
    @Json(name = "positionHeld")
    val positionHeld: String = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readParcelable(Location::class.java.classLoader),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(companyName)
        parcel.writeParcelable(location, flags)
        parcel.writeString(period)
        parcel.writeString(positionHeld)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<History> {
        override fun createFromParcel(parcel: Parcel): History {
            return History(parcel)
        }

        override fun newArray(size: Int): Array<History?> {
            return arrayOfNulls(size)
        }
    }
}