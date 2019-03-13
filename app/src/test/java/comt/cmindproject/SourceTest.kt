package comt.cmindproject

import android.util.Log
import comt.cmindproject.infrastructure.BackendIntegrator
import comt.cmindproject.infrastructure.CMINDConstants
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
class SourceTest {

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")
    private var repository = NewsRepository(BackendIntegrator.getNewsAPI())
    private lateinit var sourceResponse : SourceResponse

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        runBlocking {
            launch(Dispatchers.Main) {
                sourceResponse = repository.getSourcesAsync().await()
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
        assertEquals(CMINDConstants.OK_RESPONSE, sourceResponse.status)
    }

    @Test
    fun testErrorResponse() {
        //change the response and you can fail this test
        val responseClone = sourceResponse.copy()
        responseClone.status = CMINDConstants.ERROR_RESPONSE
        assertEquals(CMINDConstants.ERROR_RESPONSE, responseClone.status)
    }

    @Test
    fun testSourceList() {
        //change the expected and you can fail this test
        assertEquals(136, sourceResponse.sources.size)
    }

    //we need id to open articles list so is good to test it
    @Test
    fun testIfSomeIdIsNull() {
        val responseClone = sourceResponse.copy()
        // here you can fail this test -  responseClone.sources[0].id = null
        responseClone.sources.forEach {
            assertNotNull(it.id)
        }
    }
}
