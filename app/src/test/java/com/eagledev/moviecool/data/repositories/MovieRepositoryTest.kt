package com.eagledev.moviecool.data.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.eagledev.moviecool.data.db.MovieCoolDataBase
import com.eagledev.moviecool.data.db.MovieDao
import com.eagledev.moviecool.model.Movie
import com.eagledev.moviecool.network.MovieDb4Api
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.robolectric.RobolectricTestRunner

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@RunWith(RobolectricTestRunner::class)
class MovieRepositoryTest {

    private lateinit var movieDao: MovieDao
    private lateinit var db: MovieCoolDataBase

    private var mockWebServer = MockWebServer()

    private lateinit var movieDb4Api: MovieDb4Api

    private lateinit var appSharedPreferences: TestSharedPreferences

    private lateinit var movieRepository: MovieRepository

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {

        Dispatchers.setMain(mainThreadSurrogate)
        mockWebServer.start()


        appSharedPreferences = TestSharedPreferences()
        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .build()

        movieDb4Api = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(MovieDb4Api::class.java)

        val context = InstrumentationRegistry.getInstrumentation().context

        db = Room.inMemoryDatabaseBuilder(
            context, MovieCoolDataBase::class.java).build()
        movieDao = db.getMovieDao()



        movieRepository = MovieRepository(movieDb4Api, movieDao, appSharedPreferences)
    }

    @Test
    fun testSearch(){
        runBlocking(Dispatchers.IO) {
            movieDao.insertMovie(Movie(title = "Test", originalTitle = "Test", id = 1, favorite = false, rated = false))
//            println(movieDao.searchAllMovies("Test"))
            val value = movieRepository.searchMovie("Test")
            assert(getValue(value)[0] ==  Movie(title = "Test",  originalTitle = "Test", id = 1, favorite = false, rated = false) )
        }
    }

    @Test
    fun testGetFavorite(){
        runBlocking (Dispatchers.IO){
            movieDao.insertMovie(Movie(title = "Test", originalTitle = "Test", id = 2, favorite = true, rated = false))
            val value = movieRepository.getFavoritesMovies(MainScope())

            assert(getValue(value)[0] == Movie(title = "Test", originalTitle = "Test", id = 2, favorite = true, rated = false))
        }
    }

    @Test
    fun testGetRated(){
        runBlocking (Dispatchers.IO){
            movieDao.insertMovie(Movie(title = "Test", originalTitle = "Test", id = 3, favorite = true, rated = true))
            val value = movieRepository.getFavoritesMovies(MainScope())

            assert(getValue(value)[0] == Movie(title = "Test", originalTitle = "Test", id = 3, favorite = true, rated = true))
        }
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
        db.close()
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    fun <T> getValue(liveData: LiveData<T>): T {
        val data = arrayOfNulls<Any>(1)
        val latch = CountDownLatch(1)
        val observer = object : Observer<T> {
            override fun onChanged(o: T?) {
                data[0] = o
                latch.countDown()
                liveData.removeObserver(this)
            }
        }
        liveData.observeForever(observer)
        latch.await(2, TimeUnit.SECONDS)

        @Suppress("UNCHECKED_CAST")
        return data[0] as T
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> mockPagedList(list: List<T>): PagedList<T> {
        val pagedList = Mockito.mock(PagedList::class.java) as PagedList<T>
        Mockito.`when`(pagedList.get(ArgumentMatchers.anyInt())).then { invocation ->
            val index = invocation.arguments.first() as Int
            list[index]
        }
        Mockito.`when`(pagedList.size).thenReturn(list.size)
        return pagedList
    }
}