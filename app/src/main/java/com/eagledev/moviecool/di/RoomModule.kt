package com.eagledev.moviecool.di

import android.app.Application
import androidx.room.Room
import com.eagledev.moviecool.data.db.MovieCoolDataBase
import com.eagledev.moviecool.data.db.MovieDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule{

    @Singleton
    @Provides

    fun provideDB(application: Application): MovieCoolDataBase{
        return Room.databaseBuilder(application, MovieCoolDataBase::class.java, "Movie.db").fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun providesMovieDao(db: MovieCoolDataBase):MovieDao{
        return  db.getMovieDao()
    }
}