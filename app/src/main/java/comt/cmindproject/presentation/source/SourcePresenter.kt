package comt.cmindproject.presentation.source

import android.os.Handler
import comt.cmindproject.infrastructure.CMINDConstants
import comt.cmindproject.model.Source
import comt.cmindproject.presentation.base.BasePresenter
import comt.cmindproject.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SourcePresenter(private var newsRepository: NewsRepository) : BasePresenter<SourceView> {

    private var view: SourceView? = null

    override fun subscribe(view: SourceView) {
        this.view = view
    }

    override fun unSubscribe() {
        this.view = null
    }

    fun getSources() {
        GlobalScope.launch(context = Dispatchers.Main) {
            try {
                view?.showLoading()
                val sourceResponse = newsRepository.getSourcesAsync().await()
                when (sourceResponse.status) {
                    CMINDConstants.OK_RESPONSE -> view?.loadSourceList(sourceResponse.sources)
                    CMINDConstants.ERROR_RESPONSE -> view?.errorOnLoadSource()
                    else -> view?.errorOnLoadSource()
                }
            } catch (e: Exception) {
                //we can handle any exceptions here with more catch//
                view?.errorOnLoadSource()
                e.printStackTrace()
            } finally {
                view?.hideLoading()
            }
        }
    }

}