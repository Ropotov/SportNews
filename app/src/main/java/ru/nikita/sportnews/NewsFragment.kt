package ru.nikita.sportnews

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.nikita.sportnews.adapter.NewsAdapter
import ru.nikita.sportnews.databinding.FragmentNewsBinding
import ru.nikita.sportnews.model.Article
import ru.nikita.sportnews.viewmodel.MyViewModel

class NewsFragment : Fragment() {

    private lateinit var binding: FragmentNewsBinding
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(this)[MyViewModel::class.java]
        recyclerView = binding.newsRecyclerView
        adapter = NewsAdapter()
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
        viewModel.getMyNews()
        viewModel.myNewsList.observe(viewLifecycleOwner, { response ->
            response.body()?.articles?.let {
                adapter.setList(it)
            }
        })
        adapter.onNewsClickListener = object : NewsAdapter.OnNewsClickListener {
            override fun onNewsClick(article: Article, url: String) {
                super.onNewsClick(article = article, url = url)
                try {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent.data = Uri.parse(url)
                    startActivity(intent)
                } catch (e: Exception) {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.no_browser),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
        binding.swipe.setOnRefreshListener {
            viewModel.myNewsList.observe(viewLifecycleOwner, { response ->
                response.body()?.articles?.let {
                    adapter.setList(it)
                }
            })
            binding.swipe.isRefreshing = false
        }
    }
}
