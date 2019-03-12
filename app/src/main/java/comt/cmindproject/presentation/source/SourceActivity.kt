package comt.cmindproject.presentation.source

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import comt.cmindproject.R
import comt.cmindproject.model.Source

class SourceActivity : AppCompatActivity(), SourceView{


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun errorOnLoadSource() {

    }

    override fun loadSourceList(listSource: List<Source>) {

    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }


}
