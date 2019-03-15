package comt.cmindproject.repository

import comt.cmindproject.repository.api.NewsAPI
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class NewsRepository(private val api: NewsAPI) {

    suspend fun getSourcesAsync() = withContext(IO) {
        api.getSourcesAsync(API_KEY)
    }

    suspend fun getNewsByIdAsync(newsId: String, page: Int) = withContext(IO) {
        api.getNewsByIdAsync(newsId, page, PAGE_SIZE, API_KEY)
    }

    companion object {
        const val API_KEY = "46b05ebbf025459faf155cf032d071f8"
        const val PAGE_SIZE = 5
    }
}

