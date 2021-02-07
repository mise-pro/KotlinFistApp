package ru.promise.educationKtApp.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.promise.educationKtApp.model.Movie

class MoviesViewModel(
) : ViewModel() {

    private val _currentMovieList = MutableLiveData<List<Movie>>(emptyList())
    val currentMovieList : LiveData<List<Movie>> get() = _currentMovieList

    private val _selectedMovie = MutableLiveData<Movie>()
    val selectedMovie : LiveData<Movie> get() = _selectedMovie

    fun selectMovie(currentMovieListPosition:Int) {
        _selectedMovie.value =_currentMovieList.value?.get(currentMovieListPosition)
    }

    fun getCurrentMovies(){
        //TODO get films from mock or etc
    }
}