package code.vendetta.gmbn.network

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.plugins.RxJavaPlugins
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


abstract class BaseApi {

    private val gson: Gson by lazy {
        GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
    }

    protected abstract fun buildService()

    fun getClient(endpoint: String): Retrofit {
        return Retrofit.Builder()
            .client(buildOkHttpClient())
            .baseUrl(endpoint)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    }

    private fun buildOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor(YouTubeRequestInterceptor("api key")).build()
    }
}