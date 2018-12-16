package code.vendetta.gmbn.network.services

import code.vendetta.gmbn.model.FeedData
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeService {
    @GET("/youtube/v3/playlistItems")
    fun getUploads(
        @Query("part") part: String,
        @Query("maxResults") maxResults: String,
        @Query("playlistId") playlistId: String
    ): Single<Response<FeedData>>
}