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
        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
        }
        presenter.getData()

        pullToRefresh.setOnRefreshListener {
            presenter.getData()
        }

        recyclerView.addOnScrollListener(object : EndlessRecyclerOnScrollListener() {
            override fun onLoadMore() {
                progressBar.visibility = View.VISIBLE
                presenter.getData()
            }

        })

    }

    override fun populateList(data: FeedData) {
        feedAdapter.submitList(data.items)
        recyclerView.adapter = feedAdapter
        pullToRefresh.isRefreshing = false

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