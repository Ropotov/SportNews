package ru.nikita.sportnews.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.nikita.sportnews.databinding.NewsItemBinding
import ru.nikita.sportnews.model.Article

class NewsAdapter() : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    var listResponse = emptyList<Article>()

    var onNewsClickListener: OnNewsClickListener? = null

    class ViewHolder(private val binding: NewsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(position: Article) {
            with(binding) {
                titleTv.text = position.title
                if (position.author == null) authorTv.text = "SportNews" else authorTv.text = position.author.toString()
                textTv.text = position.description
            }
            Picasso.get().load(position.urlToImage).into(binding.imageNews)
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
            onNewsClickListener?.onNewsClick(pos)
        }
    }

    override fun getItemCount(): Int {
        return listResponse.size
    }

    interface OnNewsClickListener {
        fun onNewsClick(article: Article){

        }
    }
}
