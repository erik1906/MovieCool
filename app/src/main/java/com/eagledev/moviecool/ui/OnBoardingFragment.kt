package com.eagledev.moviecool.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.eagledev.moviecool.data.Result
import com.eagledev.moviecool.R
import com.eagledev.moviecool.data.AppSharedPreferences
import com.eagledev.moviecool.di.Injectable
import com.eagledev.moviecool.ui.adpters.MovieAdapter
import com.eagledev.moviecool.utils.ViewModelFactory
import kotlinx.android.synthetic.main.onboarding_fragment.*
import timber.log.Timber
import javax.inject.Inject

class OnBoardingFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: OnBoardingViewModel

    private var adapter: MovieAdapter? = null

    @Inject
    lateinit var appSharedPreferences: AppSharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.onboarding_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory).get(OnBoardingViewModel::class.java)

        initAdapter()
        viewModel.getMovies()

        viewModel.favoriteResponse.observe(viewLifecycleOwner, Observer {
            when(it){
                is Result.Success -> {
                    appSharedPreferences.onBoarding(true)
                    findNavController().navigateUp()

                }
                is Result.Error -> {

                }
                Result.Loading -> {}
            }
        })
    }

    private fun initAdapter(){
        adapter = MovieAdapter {
            Timber.d("AHhhhhh")
            viewModel.setFavorite(it.id, true)
        }

        viewModel.movieList.observe(viewLifecycleOwner, Observer {
            adapter?.submitList(it)
        })
        rv_movies.adapter = adapter
    }
}
