package dev.oscar.ruiz.cvapp.fetchcv.data.model.response


import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Class for PersonalInformation data model
 */

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
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(age)
        parcel.writeString(country)
        parcel.writeString(email)
        parcel.writeString(lastName)
        parcel.writeString(name)
        parcel.writeString(phone)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PersonalInformation> {
        override fun createFromParcel(parcel: Parcel): PersonalInformation {
            return PersonalInformation(parcel)
        }

        override fun newArray(size: Int): Array<PersonalInformation?> {
            return arrayOfNulls(size)
        }
    }
}