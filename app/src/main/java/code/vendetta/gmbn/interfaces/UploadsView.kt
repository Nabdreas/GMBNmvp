package code.vendetta.gmbn.interfaces

import code.vendetta.gmbn.model.FeedData

interface UploadsView {
    fun populateList(data: FeedData)
    fun hideProgress()
    fun showProgress()
}