package code.vendetta.gmbn.presenter

import code.vendetta.gmbn.interfaces.UploadsView
import code.vendetta.gmbn.network.api.YoutubeApi
import io.reactivex.disposables.CompositeDisposable

class UploadsPresenter(private val view: UploadsView) {

    private val compositeDisposable = CompositeDisposable()

    private val youtubeApi: YoutubeApi by lazy {
        YoutubeApi("https://www.googleapis.com")
    }

    fun getData(offset: String) {
        compositeDisposable.add(
            youtubeApi.getUploads(offset)
                .doOnSubscribe {
                    view.showProgress()
                }
                .doFinally {
                    view.hideProgress()
                }
                .subscribe({
                    it.body()?.let { it -> view.populateList(it) }
                }, {
                    view.showError()
                })
        )
    }


}