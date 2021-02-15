package ru.promise.educationKtApp.fragments

import android.content.Context
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.promise.educationKtApp.MainActivityViewModel
import ru.promise.educationKtApp.data.loadMovies
import ru.promise.educationKtApp.model.Movie

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

    fun getMovieList(context: Context) {
        //todo should be splited to 2 different scopes: IO + main
        scopeMain.launch {
            _currentMovieList.value = loadMovies(context)
        }
    }

    fun initSubscription(mainActivityViewModel: MainActivityViewModel) {
        this.mainActivityViewModel = mainActivityViewModel
    }
}