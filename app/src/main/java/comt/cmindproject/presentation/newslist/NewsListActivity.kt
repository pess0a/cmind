package comt.cmindproject.presentation.newslist

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import comt.cmindproject.R
import comt.cmindproject.infrastructure.EndlessRecyclerViewScrollListener
import comt.cmindproject.model.Article
import kotlinx.android.synthetic.main.activity_news_detail.*
import org.koin.android.ext.android.inject


class NewsListActivity : AppCompatActivity(), NewsListView {

    private val presenter: NewsListPresenter by inject()
    private lateinit var scrollListener: EndlessRecyclerViewScrollListener
    private var layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    private lateinit var newsId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        if (intent.extras != null) {
            newsId = intent.getStringExtra("newsId")
            supportActionBar?.title = newsId
            presenter.getNewsById(newsId)
        }
    }

    override fun onResume() {
        presenter.bind(this)
        super.onResume()
    }

    override fun onStop() {
        presenter.unBind()
        super.onStop()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }

    override fun loadNewsList(listArtcles: ArrayList<Article>) {
        if (recyclerViewNews.adapter==null) {
            recyclerViewNews.adapter = NewsListAdapter(this, listArtcles)
            recyclerViewNews.layoutManager = layoutManager

            scrollListener = object : EndlessRecyclerViewScrollListener(layoutManager) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                    presenter.getNewsById(newsId)
                }
            }
            recyclerViewNews.addOnScrollListener(scrollListener)
        } else {
            (recyclerViewNews.adapter as NewsListAdapter).updateList(listArtcles)
        }

    }

    override fun errorOnLoadNews() {
        textViewError.visibility = View.VISIBLE
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }
}
