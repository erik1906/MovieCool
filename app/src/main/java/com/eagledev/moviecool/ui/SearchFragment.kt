package com.eagledev.moviecool.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.eagledev.moviecool.R
import com.eagledev.moviecool.di.Injectable
import com.eagledev.moviecool.ui.adpters.MovieAdapter
import com.eagledev.moviecool.utils.ViewModelFactory
import kotlinx.android.synthetic.main.search_fragment.*
import javax.inject.Inject

class SearchFragment : Fragment(), Injectable {

    private lateinit var viewModel: SearchViewModel


    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var adapter: MovieAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider( this, viewModelFactory).get(SearchViewModel::class.java)

        searchView.isIconifiedByDefault = false

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
               viewModel.searchMovie(p0 ?: "")
                return true
            }

        })
        initAdapter()
//        viewModel.getMoviesFavorites()
    }

    private fun initAdapter(){
        adapter = MovieAdapter {
            val destination = FavoritesFragmentDirections.actionFavoritesFragmentToDetailFragment(it)
            findNavController().navigate(destination)
        }
        viewModel.movieList.observe(viewLifecycleOwner, Observer {
            adapter?.submitList(it)
        })
        rv_movies.adapter = adapter
    }
}
