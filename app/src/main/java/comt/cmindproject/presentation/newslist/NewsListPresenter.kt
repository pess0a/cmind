package comt.cmindproject.presentation.newslist

import comt.cmindproject.infrastructure.CMINDConstants
import comt.cmindproject.presentation.base.BasePresenter
import comt.cmindproject.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NewsListPresenter(private var newsRepository: NewsRepository) : BasePresenter<NewsListView> {

    private var view: NewsListView? = null

    override fun subscribe(view: NewsListView) {
        this.view = view
    }

    override fun unSubscribe() {
        this.view = null
    }

    fun getNewsById(newsId : String) {
        GlobalScope.launch(context = Dispatchers.Main) {
            try {
                view?.showLoading()
                val newsResponse = newsRepository.getNewsByIdAsync(newsId).await()
                when (newsResponse.status) {
                    CMINDConstants.OK_RESPONSE -> view?.loadNewsList(newsResponse.articles)
                    CMINDConstants.ERROR_RESPONSE -> view?.errorOnLoadNews()
                    else -> view?.errorOnLoadNews()
                }
            } catch (e: Exception) {
                //we can handle any exceptions here with more catch
                view?.errorOnLoadNews()
                e.printStackTrace()
            } finally {
                view?.hideLoading()
            }
        }
    }
}