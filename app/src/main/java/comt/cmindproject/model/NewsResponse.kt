package comt.cmindproject.model

data class NewsResponse(
    val articles: List<Article>,
    var status: String,
    val totalResults: Int
)