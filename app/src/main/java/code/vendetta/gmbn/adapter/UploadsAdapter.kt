package code.vendetta.gmbn.adapter

import android.support.v7.recyclerview.extensions.ListAdapter
import android.view.LayoutInflater
import android.view.ViewGroup
import code.vendetta.gmbn.R
import code.vendetta.gmbn.adapter.callback.UploadsDiffCallback
import code.vendetta.gmbn.adapter.viewholder.UploadsViewHolder
import code.vendetta.gmbn.model.Items
import code.vendetta.gmbn.model.Snippet

class UploadsAdapter(
    val listener: (
        snippet: Snippet
    ) -> Unit
) :
    ListAdapter<Items, UploadsViewHolder>(UploadsDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UploadsViewHolder {
        return UploadsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item_video, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: UploadsViewHolder, position: Int) {
        getItem(position).let { item ->
            with(holder) {
                itemView.tag = item
                holder.bind(item.snippet, listener)
            }
        }
    }
}