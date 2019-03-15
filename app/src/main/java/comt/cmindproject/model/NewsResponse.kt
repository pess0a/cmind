package comt.cmindproject.model

data class NewsResponse(
    val articles: ArrayList<Article>,
    var status: String,
    val totalResults: Int
)