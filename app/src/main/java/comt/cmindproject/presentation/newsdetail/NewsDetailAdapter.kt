package comt.cmindproject.presentation.newsdetail

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import comt.cmindproject.R
import comt.cmindproject.infrastructure.ImageHelper
import comt.cmindproject.model.Article
import kotlinx.android.synthetic.main.item_list_article.view.*


class NewsDetailAdapter(private val context: Context, private var listArticle : List<Article>) : Adapter<NewsDetailAdapter.ViewHolder>() {

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bindView(listArticle[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(article: Article) {
            val title = itemView.textViewTitle
            val description = itemView.textViewDescription
            val publishedDay = itemView.textViewPublished
            val author = itemView.textViewAuthor
//
//
            title.text = article.title
            description.text = article.description
            publishedDay.text = article.publishedAt.substring(0,10)
            author.text = article.author
            Log.i("logger",article.toString())
            if (article.urlToImage!=null) {
                ImageHelper.loadImage(article.urlToImage,itemView.imageViewThumbnail)
            }

        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list_article, viewGroup, false))
    }

    override fun getItemCount(): Int {
        return listArticle.size
    }


}