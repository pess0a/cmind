package comt.cmindproject.repository.api

import comt.cmindproject.model.NewsResponse
import comt.cmindproject.model.SourceResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsAPI {

    @GET("sources")
    fun getSourcesAsync(@Query("apiKey") apiKey : String): Deferred<SourceResponse>

    @GET("everything")
    fun getNewsByIdAsync(@Query("sources") newsId : String, @Query("apiKey") apiKey : String): Deferred<NewsResponse>

}