package comt.cmindproject.presentation.newslist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import comt.cmindproject.R
import comt.cmindproject.model.Article
import kotlinx.android.synthetic.main.item_list_article.view.*


class NewsListAdapter(private val context: Context, private var listArticle: ArrayList<Article>) :
    Adapter<NewsListAdapter.ViewHolder>() {

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bindView(listArticle[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(article: Article) {
            val title = itemView.textViewTitle
            val description = itemView.textViewDescription
            val publishedDay = itemView.textViewPublished
            val author = itemView.textViewAuthor

            title.text = article.title
            description.text = article.description
            publishedDay.text = article.publishedAt?.substring(0, 10)
            author.text = article.author

            if (article.author.isNullOrBlank()) {
                author.visibility = View.GONE
            } else author.visibility = View.VISIBLE

            if (article.publishedAt.isNullOrBlank()) {
                publishedDay.visibility = View.GONE
            } else publishedDay.visibility = View.VISIBLE

            if (!article.urlToImage.isNullOrBlank()) {
                itemView.imageViewThumbnail.visibility = View.VISIBLE
                itemView.imageViewThumbnail.apply { this.setImageURI(article.urlToImage) }
            } else {
                itemView.imageViewThumbnail.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list_article, viewGroup, false))
    }

    override fun getItemCount(): Int {
        return listArticle.size
    }

    fun updateList(listArticle: List<Article>) {
        this.listArticle.addAll(listArticle)
        this.notifyDataSetChanged()
    }


}