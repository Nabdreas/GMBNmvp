package code.vendetta.gmbn.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import code.vendetta.gmbn.EndlessRecyclerOnScrollListener
import code.vendetta.gmbn.R
import code.vendetta.gmbn.adapter.UploadsAdapter
import code.vendetta.gmbn.interfaces.UploadsView
import code.vendetta.gmbn.model.FeedData
import code.vendetta.gmbn.presenter.UploadsPresenter
import kotlinx.android.synthetic.main.fragment_uploads.*

class UploadsFragment : Fragment(), UploadsView {

    var isLastPage: Boolean = false
    var isLoading: Boolean = false

    private val presenter: UploadsPresenter by lazy {
        UploadsPresenter(this)
    }

    private val feedAdapter: UploadsAdapter by lazy {
        UploadsAdapter { snippet ->
            val fragment = VideoDetailsFragment.newInstance(snippet)
            openDetailed(fragment)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_uploads, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager
        presenter.getData()

        pullToRefresh.setOnRefreshListener {
            presenter.getData()
        }

        recyclerView?.addOnScrollListener(object : EndlessRecyclerOnScrollListener(layoutManager) {
            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun loadMoreItems() {
                isLoading = true
                presenter.getData()
            }
        })

    }

    override fun populateList(data: FeedData) {
        recyclerView.visibility = View.VISIBLE
        errorContainer.visibility = View.GONE
        pullToRefresh.isRefreshing = false

        feedAdapter.submitList(data.items)
        recyclerView.adapter = feedAdapter
    }

    override fun showError() {
        errorContainer.visibility = View.VISIBLE
        pullToRefresh.isRefreshing = false
        recyclerView.visibility = View.GONE
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    private fun openDetailed(fragment: VideoDetailsFragment) {
        activity?.supportFragmentManager?.beginTransaction()?.replace(
            R.id.container, fragment
        )
            ?.addToBackStack(null)
            ?.commit()
    }


    companion object {
        fun newInstance(): Fragment {
            val args = Bundle()
            val fragment = UploadsFragment()
            fragment.arguments = args
            return fragment
        }
    }
}