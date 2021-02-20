package ru.promise.educationKtApp.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.promise.educationKtApp.MainActivityViewModel
import ru.promise.educationKtApp.model.Movie
import ru.promise.networktest.netModule.NetworkModule

class MovieListViewModel(
) : ViewModel() {
    private var mainActivityViewModel: MainActivityViewModel? = null

    private val scopeMain = CoroutineScope(Dispatchers.Main)

    private val _currentMovieList = MutableLiveData<List<Movie>>(emptyList())
    val currentMovieList: LiveData<List<Movie>> get() = _currentMovieList

    //todo should be switched to id instead of position
    fun selectMovie(currentMovieListPosition: Int) {
        val movie = _currentMovieList.value?.get(currentMovieListPosition)
        NetworkModule().getMovieDetails(movie!!,this)
    }

    fun getMovieList() {
        NetworkModule().getMoviesPopular(this)
    }

    fun receiveNewMovies(movies: List<Movie>) {
        scopeMain.launch {
            _currentMovieList.value=movies
        }
    }

    fun receiveNewSelectedMovies(movie: Movie) {
        scopeMain.launch {
            mainActivityViewModel?.toMovieDetails(movie)
        }
    }

    fun initSubscription(mainActivityViewModel: MainActivityViewModel) {
        this.mainActivityViewModel = mainActivityViewModel
    }
}