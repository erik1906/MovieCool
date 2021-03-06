package com.eagledev.moviecool.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.paging.PagedList

import com.eagledev.moviecool.R
import com.eagledev.moviecool.data.AppSharedPreferences
import com.eagledev.moviecool.utils.ViewModelFactory
import com.eagledev.moviecool.data.Result
import com.eagledev.moviecool.di.Injectable
import com.eagledev.moviecool.ui.adpters.MovieAdapter
import kotlinx.android.synthetic.main.log_in_fragment.*
import kotlinx.android.synthetic.main.movies_fragment.*
import timber.log.Timber

import javax.inject.Inject

class RecommendationsFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: RecommendationsViewModel

    @Inject
    lateinit var appSharedPreferences: AppSharedPreferences

    private var adapter: MovieAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movies_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider( this, viewModelFactory).get(RecommendationsViewModel::class.java)


        //Send to Onboarding fragment and select a favorite to load data
        if(!appSharedPreferences.onBoarding()){
            val directions = RecommendationsFragmentDirections.actionRecommendationsFragmentToOnBoardingFragment()
            findNavController().navigate(directions)

        }

        initAdapter()
        viewModel.getMoviesRecommended()
    }

    private fun initAdapter(){
        adapter = MovieAdapter {
            val destination = RecommendationsFragmentDirections.actionRecommendationsFragmentToDetailFragment3(it)
            findNavController().navigate(destination)
        }
        viewModel.movieList.observe(viewLifecycleOwner, Observer {
            adapter?.submitList(it)
        })
        rv_movies.adapter = adapter
    }
}
