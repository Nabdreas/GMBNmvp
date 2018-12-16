package code.vendetta.gmbn.adapter.callback

import android.support.v7.util.DiffUtil
import code.vendetta.gmbn.model.Items

class UploadsDiffCallback : DiffUtil.ItemCallback<Items>() {
    override fun areItemsTheSame(oldItem: Items?, newItem: Items?): Boolean {
        return oldItem?.snippet == newItem?.snippet
    }

    override fun areContentsTheSame(oldItem: Items?, newItem: Items?): Boolean {
        return oldItem == newItem
    }
}