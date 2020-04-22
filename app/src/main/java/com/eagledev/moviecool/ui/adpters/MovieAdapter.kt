package com.eagledev.moviecool.ui.adpters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.eagledev.moviecool.model.Movie

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
                val view = LayoutInflater.from(parent.context).inflate(,parent,false)
                return MovieViewHolder(view)
            }
        }
        var movie: Movie? = null

        fun bind(movie: Movie?, listener: (Movie) -> Unit){
            movie?.let{
                this.movie = movie

                view.setOnClickListener {
                    listener(it)
                }
            }
        }

    }


}