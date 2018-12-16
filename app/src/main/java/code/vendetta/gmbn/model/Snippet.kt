package code.vendetta.gmbn.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Snippet(
    var title: String = "",
    var description: String = "",
    @SerializedName("publishedAt")
    var published: String = "",
    var thumbnails: Thumbnails = Thumbnails(),
    @SerializedName("resourceId")
    var resourceId: ResourceId = ResourceId()
) : Parcelable