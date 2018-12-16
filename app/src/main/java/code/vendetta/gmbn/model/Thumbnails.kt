package code.vendetta.gmbn.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Thumbnails(
    val default: Default = Default(),
    val high: High = High()
) : Parcelable