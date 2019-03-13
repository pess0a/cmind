package comt.cmindproject

import comt.cmindproject.repository.NewsRepository
import kotlinx.coroutines.CompletableDeferred
import org.mockito.Mockito

open class BaseTest {
    fun <T> T.toDeferred() = CompletableDeferred(this)
    var repoMock: NewsRepository = Mockito.mock(NewsRepository::class.java)
}