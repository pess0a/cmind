package comt.cmindproject.presentation.newslist

import comt.cmindproject.model.Article
import comt.cmindproject.presentation.base.View

interface NewsListView : View {
    fun errorOnLoadNews()
    fun loadNewsList(listArtcles: ArrayList<Article>)
}