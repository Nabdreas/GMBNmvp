package code.vendetta.gmbn

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

/**
 * Abstract custom RecyclerView.OnScrollListener that listens for endless scrolling events.
 */
abstract class EndlessRecyclerOnScrollListener : RecyclerView.OnScrollListener() {

    private var previousTotal = 0 // The total number of items in the dataset after the last load
    private var loading = true // True if we are still waiting for the last set of data to load.

    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount = recyclerView!!.childCount
        val totalItemCount = recyclerView.layoutManager.itemCount
        val firstVisibleItem = (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

        val visibleThreshold = 5
        if (!loading && totalItemCount - visibleItemCount <= firstVisibleItem + visibleThreshold) {
            // End has been reached

            onLoadMore()

            loading = true
        }
    }

    /**
     * Called when the list has reached a point where more data is to be loaded.
     *
     * @param currentPage int: The current page number
     */
    abstract fun onLoadMore()

}
