package comt.cmindproject.model

data class Source(
    val category: String? = "",
    val country: String? = "",
    val description: String? = "",
    var id: String? = "",
    val language: String? = "",
    val name: String? = "",
    val url: String? = ""
)