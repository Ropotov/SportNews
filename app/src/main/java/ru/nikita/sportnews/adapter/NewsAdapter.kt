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

    class ViewHolder(private val binding: NewsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Article) {
            with(binding) {
                titleTv.text = position.title
                if (position.author == null) {
                    authorTv.text = ""
                } else {
                    authorTv.text = position.author.toString()
                }
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
        val position = listResponse[position]
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return listResponse.size
    }
}


