package comt.cmindproject.presentation.newsdetail

import comt.cmindproject.model.Article
import comt.cmindproject.presentation.base.BaseView

interface NewsListView : BaseView {
    fun errorOnLoadNews()
    fun loadNewsList(listArtcles : List<Article>)
}