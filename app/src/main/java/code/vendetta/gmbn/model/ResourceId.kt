package code.vendetta.gmbn.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResourceId(
    var kind: String = "",
    @SerializedName("videoId")
    var videoId: String = ""
) : Parcelable