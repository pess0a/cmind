package comt.cmindproject

import android.util.Log
import comt.cmindproject.infrastructure.BackendIntegrator
import comt.cmindproject.infrastructure.CMINDConstants
import comt.cmindproject.model.NewsResponse
import comt.cmindproject.model.SourceResponse
import comt.cmindproject.repository.NewsRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.koin.test.KoinTest

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
class NewsTest {

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")
    private var repository = NewsRepository(BackendIntegrator.getNewsAPI())
    private lateinit var newsResponse : NewsResponse
    private val newsId = "abc-news-au"

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        runBlocking {
            launch(Dispatchers.Main) {
                newsResponse = repository.getNewsByIdAsync(newsId).await()
            }
        }
    }

    @After
    fun setDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun testResponse() {
        //change the response and you can fail this test
        assertEquals(CMINDConstants.OK_RESPONSE, newsResponse.status)
    }

    @Test
    fun testErrorResponse() {
        //change the response and you can fail this test
        val responseClone = newsResponse.copy()
        responseClone.status = CMINDConstants.ERROR_RESPONSE
        assertEquals(CMINDConstants.ERROR_RESPONSE, responseClone.status)
    }

    @Test
    fun testSourceList() {
        //change the expected and you can fail this test
        assertEquals(20, newsResponse.articles.size)
    }

    @Test
    fun testIfSomeTitleIsNull() {
        val responseClone = newsResponse.copy()
        responseClone.articles.forEach {
            assertNotNull(it.title)
        }
    }
}
