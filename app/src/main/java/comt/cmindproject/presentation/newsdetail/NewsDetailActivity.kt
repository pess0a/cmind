package comt.cmindproject.presentation.newsdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import comt.cmindproject.R

class NewsDetailActivity : AppCompatActivity() {
    private lateinit var newsId : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)

        if(intent.extras!=null) {
            newsId = intent.getStringExtra("newsId")
        }
    }
}
