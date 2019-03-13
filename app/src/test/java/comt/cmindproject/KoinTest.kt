package comt.cmindproject;

import comt.cmindproject.di.networkModule
import comt.cmindproject.di.newsModule
import org.junit.Before
import org.junit.Test
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.test.KoinTest
import org.koin.test.checkModules

class KoinTest : KoinTest {

    @Before
    fun before() {
        startKoin(listOf(newsModule, networkModule))
    }

    @Test
    fun checkModules() {
        // if you remove some dependency ( you can remove " factory { NewsRepository(get()) } " to test it )
        // on AppModule, this test should fail
        checkModules(listOf(newsModule, networkModule))
    }

}