package comt.cmindproject.repository

import comt.cmindproject.model.SourceResponse
import comt.cmindproject.repository.api.NewsAPI
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class NewsRepository(private val api: NewsAPI) {
    suspend fun getSourcesAsync() = withContext(IO) { async { api.getSourcesAsync(API_KEY).await() } }
    suspend fun getNewsByIdAsync(newsId : String) = withContext(IO) { async { api.getNewsByIdAsync(newsId, API_KEY).await() } }

    fun testeA(): SourceResponse{
        return SourceResponse(arrayListOf(),"ok")
    }
    companion object {
        const val API_KEY = "46b05ebbf025459faf155cf032d071f8"
    }
}

