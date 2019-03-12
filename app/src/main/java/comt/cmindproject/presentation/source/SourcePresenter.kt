package comt.cmindproject.presentation.source

import comt.cmindproject.infrastructure.CMINDConstants
import comt.cmindproject.presentation.base.BasePresenter
import comt.cmindproject.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SourcePresenter(private var newsRepository: NewsRepository) : BasePresenter<SourceView> {

    private var view : SourceView? = null

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
                val sourcesResponse = newsRepository.getSourcesAsync().await()
                if(sourcesResponse.status==CMINDConstants.OK_RESPONSE) {
                    view?.loadSourceList(sourcesResponse.sources)
                } else {
                    view?.errorOnLoadSource()
                }
            } catch (e: Exception) {
                //we can handle any exceptions here.
                view?.errorOnLoadSource()
                e.printStackTrace()
            } finally {
                view?.hideLoading()
            }
        }
    }
}