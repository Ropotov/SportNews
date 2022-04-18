package ru.nikita.sportnews.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.nikita.sportnews.R
import ru.nikita.sportnews.databinding.NewsItemBinding
import ru.nikita.sportnews.model.Article

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private var listResponse = emptyList<Article>()

    var onNewsClickListener: OnNewsClickListener? = null

    class ViewHolder(private val binding: NewsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var url = ""

        fun bind(article: Article) {
            with(binding) {
                titleTv.text = article.title
                authorTv.text = article.author ?: "Sport News"
                textTv.text = article.description ?: "Подробнее на сайте"
            }
            article.urlToImage?.let {
                Picasso.get().load(article.urlToImage).into(binding.imageNews)
            } ?: binding.imageNews.setImageResource(R.drawable.logo_zag)
            url = article.url
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<Article>) {
        listResponse = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = NewsItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pos = listResponse[position]
        holder.bind(pos)
        holder.itemView.setOnClickListener {
            onNewsClickListener?.onNewsClick(pos, holder.url)
        }
    }

    override fun getItemCount(): Int {
        return listResponse.size
    }

    interface OnNewsClickListener {
        fun onNewsClick(article: Article, url: String){

        }
    }
}
