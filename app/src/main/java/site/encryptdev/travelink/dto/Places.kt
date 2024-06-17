package site.encryptdev.travelink.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Places(
    val placeId: Int,
    val photo : String,
    val placeName: String,
    val city: String,
    val description: String,
    val rating: Double

) : Parcelable
