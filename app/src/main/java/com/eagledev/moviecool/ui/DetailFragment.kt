package com.eagledev.moviecool.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide

import com.eagledev.moviecool.R
import com.eagledev.moviecool.di.Injectable
import com.eagledev.moviecool.utils.ViewModelFactory
import kotlinx.android.synthetic.main.detail_fragment.*
import javax.inject.Inject

class DetailFragment : Fragment(), Injectable {

    val args: DetailFragmentArgs by navArgs()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider( this, viewModelFactory).get(DetailViewModel::class.java)

        val movie = args.movie


        Glide.with(view).load("https://image.tmdb.org/t/p/w500${movie.posterPath}").into(iv_poster)

        tv_tittle.text = movie.originalTitle

        tv_average.text = movie.voteAverage.toString()

        tv_overview.text = movie.overview

        tv_date.text = movie.releaseDate

        val fav = movie.favorite
        if(fav != null && fav){
            iv_fav.setImageDrawable(view.context.getDrawable(R.drawable.fav_star))
        }else{
            iv_fav.setImageDrawable(view.context.getDrawable(R.drawable.star))
        }
    }

}
