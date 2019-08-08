package dev.oscar.ruiz.cvapp.fetchcv.data.model.response


import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Class for Control data model
 */

@JsonClass(generateAdapter = true)
data class Control(
    @Json(name = "isLookingJob")
    val isLookingJob: Boolean = false,
    @Json(name = "lastUpdate")
    val lastUpdate: String = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readByte() != 0.toByte(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeByte(if (isLookingJob) 1 else 0)
        parcel.writeString(lastUpdate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Control> {
        override fun createFromParcel(parcel: Parcel): Control {
            return Control(parcel)
        }

        override fun newArray(size: Int): Array<Control?> {
            return arrayOfNulls(size)
        }
    }
}