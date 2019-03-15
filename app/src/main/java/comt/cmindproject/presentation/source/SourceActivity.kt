package comt.cmindproject.presentation.source

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import comt.cmindproject.R
import comt.cmindproject.model.Source
import comt.cmindproject.presentation.newslist.NewsListActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject


class SourceActivity : AppCompatActivity(), SourceView {

    private val presenter: SourcePresenter by inject()
    private lateinit var sourceAdapter: SourceAdapter
    private var layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        presenter.getSources()
    }

    override fun onResume() {
        presenter.subscribe(this)
        super.onResume()
    }

    override fun onStop() {
        presenter.unSubscribe()
        super.onStop()
    }

    override fun errorOnLoadSource() {
        textViewError.apply {
            visibility = View.VISIBLE
            setOnClickListener { presenter.getSources() }
        }
    }

    override fun loadSourceList(listSource: List<Source>) {
        textViewError.visibility = View.GONE
        recyclerViewSource.adapter = SourceAdapter(this, listSource).apply { sourceAdapter = this }
        recyclerViewSource.layoutManager = layoutManager

        sourceAdapter.setSourceClickItemListener {
            startActivity(Intent(this@SourceActivity, NewsListActivity::class.java).apply {
                putExtra("newsId", it)
            })
        }
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }


}
