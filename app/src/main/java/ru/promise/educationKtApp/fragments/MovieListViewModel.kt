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

    private val scopeMain = CoroutineScope(Dispatchers.Main)

    private val _currentMovieList = MutableLiveData<List<Movie>>(emptyList())
    val currentMovieList: LiveData<List<Movie>> get() = _currentMovieList

    private val _selectedMovie = MutableLiveData<Movie>()
    val selectedMovie: LiveData<Movie> get() = _selectedMovie

    fun selectMovie(currentMovieListPosition: Int) {
        _selectedMovie.value = _currentMovieList.value?.get(currentMovieListPosition)
        Log.d("selectMovie", currentMovieListPosition.toString())
    }

    fun getMovieList(context: Context) {
        //todo why main scope?
        scopeMain.launch {
            _currentMovieList.value = loadMovies(context)
        }
    }
}