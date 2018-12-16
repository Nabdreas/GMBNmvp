package code.vendetta.gmbn

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView


/**
 * Pagination class to add more items to the list when reach the last item.
 */
abstract class EndlessRecyclerOnScrollListener
    (var layoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {

    private var previousTotal = 0
    private val visibleThreshold = 10
    private var currentPage = 1
    private var loading = true

    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false
                previousTotal = totalItemCount
            }
        }

        if (!loading && totalItemCount - visibleItemCount <= firstVisibleItemPosition + visibleThreshold) {
            currentPage++
            loadMoreItems(currentPage, totalItemCount)
            loading = true
        }

    }

    fun resetState() {
        currentPage = 1
        previousTotal = 0
        loading = true
    }

    abstract fun loadMoreItems(current: Int, totalItemCount: Int)

}
