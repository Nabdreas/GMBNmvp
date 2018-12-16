package code.vendetta.gmbn.adapter.viewholder

import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View
import code.vendetta.gmbn.R
import code.vendetta.gmbn.model.Snippet
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_video.view.*

class UploadsViewHolder(
    itemView: View
) : ViewHolder(itemView) {

    var videoData: Snippet = Snippet()

    fun bind(
        videoData: Snippet,
        listener: (
            snippet: Snippet
        ) -> Unit
    ) = with(itemView) {
        this@UploadsViewHolder.videoData = videoData
        videoTitle.text = videoData.title

        Picasso.get()
            .load(videoData.thumbnails.default.url)
            .into(thumbnail)

        setOnClickListener {
            listener(
                videoData
            )
        }
    }


}