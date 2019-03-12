package comt.cmindproject.repository

import comt.cmindproject.repository.api.NewsAPI
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class NewsRepository(private val api: NewsAPI) {
    suspend fun getSourcesAsync() = withContext(IO) { async { api.getSourcesAsync(API_KEY).await() } }
    suspend fun getNewsBySourceAsync(source : String) = withContext(IO) { async { api.getNewsBySourceAsync(source, API_KEY).await() } }

    companion object {
        const val API_KEY = "46b05ebbf025459faf155cf032d071f8"
    }
}

