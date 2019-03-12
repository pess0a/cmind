package comt.cmindproject.repository.api

import comt.cmindproject.model.News
import comt.cmindproject.model.SourceResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsAPI {

    @GET("sources")
    fun getSourcesAsync(@Query("apiKey") apiKey : String): Deferred<SourceResponse>

    @GET("everything?sources={source}")
    fun getNewsBySourceAsync(@Path("source") source : String, @Query("apiKey") apiKey : String): Deferred<News>

}