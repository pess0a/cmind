package comt.cmindproject.model

data class Article(
    val author: String? = "",
    val content: String? = "",
    val description: String? = "",
    val publishedAt: String? = "",
    val source: SourceArticle? = SourceArticle(),
    var title: String? = "",
    val url: String? = "",
    val urlToImage: String? = ""
)