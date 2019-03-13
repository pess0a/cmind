package comt.cmindproject.unit

import comt.cmindproject.BaseTest
import comt.cmindproject.infrastructure.CMINDConstants
import comt.cmindproject.model.Article
import comt.cmindproject.model.NewsResponse
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito.`when`

class NewsTest : BaseTest() {

    @Test
    fun testResponse() {
        runBlocking {
            val newsResponseMock = NewsResponse(arrayListOf(), "ok", 1)
            `when`(repoMock.getNewsByIdAsync("1")).thenReturn(newsResponseMock.toDeferred())
            assertEquals(CMINDConstants.OK_RESPONSE, repoMock.getNewsByIdAsync("1").await().status)
        }
    }

    @Test
    fun testErrorResponse() {
        val newsResponseMock = NewsResponse(arrayListOf(), "error", 1)

        runBlocking {
            `when`(repoMock.getNewsByIdAsync("1")).thenReturn(newsResponseMock.toDeferred())
            assertEquals(CMINDConstants.ERROR_RESPONSE, repoMock.getNewsByIdAsync("1").await().status)

        }
    }

    @Test
    fun testSourceList() {
        val listArticle = arrayListOf<Article>().apply {
            apply {
                add(Article())
                add(Article())
                add(Article())
            }
        }
        val newsResponseMock = NewsResponse(listArticle, "ok", 1)

        runBlocking {
            `when`(repoMock.getNewsByIdAsync("1")).thenReturn(newsResponseMock.toDeferred())
            assertEquals(3, repoMock.getNewsByIdAsync("1").await().articles.size)
        }
    }

    //we need id to open articles list so is good to test it
    @Test
    fun testIfSomeTitleIsNull() {
        val listArticle = arrayListOf<Article>().apply {
            apply {
                add(Article().apply { this.title = "title 1" })
                add(Article().apply { this.title = "title 2" })
                add(Article().apply { this.title = "title 3" })
                // you can remove this to fail the test
                //add(Article().apply { this.title = null })
            }
        }
        val newsResponseMock = NewsResponse(listArticle, "ok", 1)

        runBlocking {
            `when`(repoMock.getNewsByIdAsync("1")).thenReturn(newsResponseMock.toDeferred())
            newsResponseMock.articles.forEach {
                assertNotNull(it.title)
            }
        }
    }
}
