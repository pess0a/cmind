package comt.cmindproject.presentation.source

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import comt.cmindproject.R
import comt.cmindproject.model.Source
import kotlinx.android.synthetic.main.item_list_source.view.*


class SourceAdapter(private val context: Context, private var listSource: List<Source>) :
    Adapter<SourceAdapter.ViewHolder>() {


    lateinit var listener: OnSourceClickItemListener

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bindView(listSource[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(source: Source) {
            val title = itemView.textViewTitle
            val description = itemView.textViewDescription
            val url = itemView.textViewUrl

            title.text = source.name
            description.text = source.description
            url.text = source.url
            if (source.id != null) itemView.setOnClickListener { listener.onClick(source.id!!) }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list_source, viewGroup, false))
    }

    override fun getItemCount(): Int {
        return listSource.size
    }

    interface OnSourceClickItemListener {
        fun onClick(newsId: String) = Unit
    }

    inline fun setSourceClickItemListener(crossinline listener: (String) -> Unit) {
        this.listener = object : OnSourceClickItemListener {
            override fun onClick(newsId: String) = listener(newsId)
        }
    }


}