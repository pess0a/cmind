package comt.cmindproject

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import comt.cmindproject.di.networkModule
import comt.cmindproject.di.newsModule
import org.koin.android.ext.android.startKoin

class CMINDApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
        startKoin(this, listOf(newsModule,networkModule))
    }
}