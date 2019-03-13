package comt.cmindproject.presentation.newslist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import comt.cmindproject.R
import kotlinx.android.synthetic.main.activity_news_detail.*
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import comt.cmindproject.model.Article
import org.koin.android.ext.android.inject


class NewsListActivity : AppCompatActivity(), NewsListView {

    private val presenter : NewsListPresenter by inject()
    private var newsListAdapter : NewsListAdapter? = null
    private var layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    private lateinit var newsId : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)
        setSupportActionBar(toolbar)

        if (supportActionBar != null) {
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                setDisplayShowHomeEnabled(true)
            }
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

    override fun loadNewsList(listArtcles : List<Article>) {
        textViewError.visibility = View.GONE
        recyclerViewNews.adapter = NewsListAdapter(this,listArtcles).apply {  newsListAdapter = this}
        recyclerViewNews.layoutManager = layoutManager
    }

    override fun errorOnLoadNews() {
        textViewError.apply {
            visibility = View.VISIBLE
            setOnClickListener { presenter.getNewsById(newsId) }
        }
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }
}
