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

    fun selectMovie(currentMovieListPosition: Int) {
        val movie = _currentMovieList.value?.get(currentMovieListPosition)
        mainActivityViewModel?.toMovieDetails(movie!!)
    }

    fun getMovieList() {
        //todo should be splited to 2 different scopes: IO + main
        scopeMain.launch {
            //_currentMovieList.value = loadMovies(context)
        }
        NetworkModule().getMoviesPopular(this)
    }

    fun receiveResult(movies: List<Movie>) {
        scopeMain.launch {
            _currentMovieList.value=movies
        }
    }



    fun initSubscription(mainActivityViewModel: MainActivityViewModel) {
        this.mainActivityViewModel = mainActivityViewModel
    }
}