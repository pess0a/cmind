package comt.cmindproject.presentation.newsdetail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import comt.cmindproject.R
import kotlinx.android.synthetic.main.activity_news_detail.*
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import comt.cmindproject.model.Article
import comt.cmindproject.presentation.source.SourceAdapter
import org.koin.android.ext.android.inject


class NewsListActivity : AppCompatActivity(), NewsListView {

    private val presenter : NewsListPresenter by inject()
    private var newsDetailAdapter : NewsDetailAdapter? = null
    private var layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    private lateinit var newsId : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)
        setSupportActionBar(toolbar)

        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
        }

        if(intent.extras!=null) {
            newsId = intent.getStringExtra("newsId")
            supportActionBar?.title = newsId
            presenter.getNewsById(newsId)
        }
    }

    override fun onResume() {
        presenter.subscribe(this)
        super.onResume()
    }

    override fun onStop() {
        super.onStop()
        presenter.unSubscribe()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }

    override fun errorOnLoadNews() {

    }

    override fun loadNewsList(listArtcles : List<Article>) {
        recyclerViewNews.adapter = NewsDetailAdapter(this,listArtcles).apply {  newsDetailAdapter = this}
        recyclerViewNews.layoutManager = layoutManager
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }
}
