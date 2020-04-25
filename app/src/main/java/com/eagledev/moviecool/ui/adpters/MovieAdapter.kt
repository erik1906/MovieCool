package com.eagledev.moviecool.ui.adpters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eagledev.moviecool.R
import com.eagledev.moviecool.model.Movie
import kotlinx.android.synthetic.main.movie_card.view.*

class MovieAdapter( private val listener: (Movie)->Unit): PagedListAdapter<Movie, RecyclerView.ViewHolder>(MOVIE_COMPARATOR){

    companion object{
        private val MOVIE_COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem.id == newItem.id


            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem == newItem

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return  MovieViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val movie = getItem(position)

        if(movie != null){
            (holder as MovieViewHolder).bind(movie, listener)
        }
    }

    class MovieViewHolder(private val view: View): RecyclerView.ViewHolder(view){
        companion object{
            fun create(parent: ViewGroup): MovieViewHolder{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_card,parent,false)
                return MovieViewHolder(view)
            }
        }
        var movie: Movie? = null

        fun bind(mov: Movie?, listener: (Movie) -> Unit){
            mov?.let{movie ->
                this.movie = movie

                Glide.with(view).load("https://image.tmdb.org/t/p/w500${movie.posterPath}").into(view.imageView)

                val fav = movie.favorite
                if(fav){
                    view.iv_fav.setImageDrawable(view.context.getDrawable(R.drawable.fav_star))
                }else{
                    view.iv_fav.setImageDrawable(view.context.getDrawable(R.drawable.star))
                }

                view.tv_rate.text = movie.voteAverage.toString()

                view.tv_movie_name.text = movie.originalTitle
                view.setOnClickListener {
                    listener(movie)
                }
            }
        }

    }


}