package com.yogeshandroid.mvvm_daggerhilt_retrofit.views.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yogeshandroid.mvvm_daggerhilt_retrofit.databinding.FragmentHomeFragmentBinding
import com.yogeshandroid.mvvm_daggerhilt_retrofit.utils.Constants
import com.yogeshandroid.mvvm_daggerhilt_retrofit.utils.RecyclerViewItemClickListener
import com.yogeshandroid.mvvm_daggerhilt_retrofit.views.fragments.home.adapters.TopMoviesAdapter
import com.yogeshandroid.mvvm_daggerhilt_retrofit.views.fragments.home.models.TopMoviesResp
import com.yogeshandroid.mvvm_daggerhilt_retrofit.views.fragments.home.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment(), RecyclerViewItemClickListener {
    lateinit var binding: FragmentHomeFragmentBinding
    lateinit var topMoviesList: ArrayList<TopMoviesResp.Results>
    var isNextPage: Boolean = false
    lateinit var adapter: TopMoviesAdapter
    var llmTopMovies: LinearLayoutManager =
        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    private val viewModel: HomeViewModel by viewModels()

    companion object {
        @JvmStatic
        var page: Int = 1
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeFragmentBinding.inflate(inflater, container, false)
        initSetup()
        attachObservers()
        initPagination()


        callInitApi(1)
        return binding.root
    }

    private fun initPagination() {
        binding.topMoviesRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (llmTopMovies.findLastVisibleItemPosition() == topMoviesList.size - 1) {
                    if (isNextPage) {
                        ++page
                        callInitApi(page)
                        isNextPage = false
                    }
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

        })
    }

    private fun initSetup() {
        page = 1
        topMoviesList = arrayListOf()
        adapter = TopMoviesAdapter(context, topMoviesList, this)
        binding.topMoviesRecyclerView.adapter = adapter
        binding.topMoviesRecyclerView.layoutManager = llmTopMovies
    }

    private fun attachObservers() {
        viewModel.failureString.observe(viewLifecycleOwner) {
            binding.progress.visibility = View.GONE
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }

        viewModel.topMoviesResp.observe(viewLifecycleOwner) {
            binding.progress.visibility = View.GONE

            isNextPage = it.totalPages!! > page

            if (it.page == 1) {
                topMoviesList.clear()
                topMoviesList.addAll(it.results)
            } else {
                topMoviesList.addAll(it.results)
            }
            adapter.notifyDataSetChanged()
        }
    }

    private fun callInitApi(page: Int) {
        viewModel.topMovies(
            true,
            false,
            "en-US",
            page,
            "popularity.desc",
            "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzMzE1MDI4ODBkNDViYWE1NzFlMTIyZDk0NzYzNDA4ZCIsInN1YiI6IjY1N2FiMzBjNGQyM2RkMDBjNmFiMGQyMiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.pNuh4Pd7vXp90q1S-v1whh1PJ77orlBmiJwRF_AqV4U"
        )
        binding.progress.visibility = View.VISIBLE
    }

    override fun onClick(position: Int, type: String) {
        if (type.equals(Constants.TOP_MOVIES)) {
            Toast.makeText(context, topMoviesList.get(position).overview, Toast.LENGTH_SHORT).show()
        }
    }
}