package comt.cmindproject.presentation.source

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import comt.cmindproject.R
import comt.cmindproject.model.Source
import comt.cmindproject.presentation.newsdetail.NewsDetailActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject


class SourceActivity : AppCompatActivity(), SourceView{

    private val presenter : SourcePresenter by inject()
    private var sourceAdapter : SourceAdapter? = null
    private var layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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

    }

    override fun loadSourceList(listSource: List<Source>) {
        recyclerViewSource.adapter = SourceAdapter(this,listSource).apply {  sourceAdapter = this}
        recyclerViewSource.layoutManager = layoutManager

        sourceAdapter?.setSourceClickItemListener(object : SourceAdapter.OnSourceClickItemListener {
            override fun onClick(newsId: String) {
                startActivity(Intent(this@SourceActivity,NewsDetailActivity::class.java).apply {
                    this.putExtra("newsId",newsId)
                })
            }
        })
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }


}
