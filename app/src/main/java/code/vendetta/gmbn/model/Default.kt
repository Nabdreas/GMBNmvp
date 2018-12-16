package code.vendetta.gmbn.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Default(
    val url: String = "",
    val width: Int = 0,
    val height: Int = 0
) : Parcelable