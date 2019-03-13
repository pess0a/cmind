package comt.cmindproject.unit

import comt.cmindproject.BaseTest
import comt.cmindproject.infrastructure.CMINDConstants
import comt.cmindproject.model.Source
import comt.cmindproject.model.SourceResponse
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`

class SourceTest : BaseTest() {

    @Test
    fun testResponse() {
        val sourceResponse = SourceResponse(arrayListOf(), "ok")
        runBlocking {
            `when`(repoMock.getSourcesAsync()).thenReturn(sourceResponse.toDeferred())
            assertEquals(CMINDConstants.OK_RESPONSE, repoMock.getSourcesAsync().await().status)
        }
    }

    @Test
    fun testErrorResponse() {
        val sourceResponse = SourceResponse(arrayListOf(), "error")

        runBlocking {
            `when`(repoMock.getSourcesAsync()).thenReturn(sourceResponse.toDeferred())
            assertEquals(CMINDConstants.ERROR_RESPONSE, repoMock.getSourcesAsync().await().status)
        }
    }

    @Test
    fun testSourceList() {
        val listSource = arrayListOf<Source>().apply {
            apply {
                add(Source())
                add(Source())
                add(Source())
            }
        }
        val sourceResponse = SourceResponse(listSource, "ok")

        runBlocking {
            `when`(repoMock.getSourcesAsync()).thenReturn(sourceResponse.toDeferred())
            assertEquals(3, repoMock.getSourcesAsync().await().sources.size)
        }
    }

    //we need id to open articles list so is good to test it
    @Test
    fun testIfSomeIdIsNull() {
        val listSource = arrayListOf<Source>().apply {
            apply {
                add(Source())
                add(Source())
                add(Source())
//                 you can remove this to fail the test
//                add(Source().apply { this.id = null })
            }
        }
        val sourceResponse = SourceResponse(listSource, "ok")

        runBlocking {
            Mockito.`when`(repoMock.getSourcesAsync()).thenReturn(sourceResponse.toDeferred())
            sourceResponse.sources.forEach {
                assertNotNull(it.id)
            }
        }
    }
}
