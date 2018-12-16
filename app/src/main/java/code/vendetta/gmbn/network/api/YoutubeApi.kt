package code.vendetta.gmbn.network.api

import code.vendetta.gmbn.model.FeedData
import code.vendetta.gmbn.network.BaseApi
import code.vendetta.gmbn.network.services.YoutubeService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class YoutubeApi(private val endpoint: String) : BaseApi() {
    private lateinit var youtubeService: YoutubeService

    init {
        buildService()
    }

    override fun buildService() {
        try {
            youtubeService = getClient(endpoint).create<YoutubeService>(YoutubeService::class.java)

        } catch (e: Exception) {
            throw IllegalStateException("Error creating api: " + e.message)
        }
    }

    fun getUploads(): Single<Response<FeedData>> {
        return youtubeService.getUploads(
            "snippet",
            "25",
            "UU_A--fhX5gea0i4UtpD99Gg"

        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}