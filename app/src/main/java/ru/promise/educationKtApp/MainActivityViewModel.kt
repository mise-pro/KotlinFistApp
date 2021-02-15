package ru.promise.educationKtApp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.promise.educationKtApp.model.Movie

class MainActivityViewModel(
) : ViewModel() {

    private val _activityState = MutableLiveData<State>(State.MovieList())
    val activityState: LiveData<State> get() = _activityState

    fun toMovieList() {
        _activityState.setValue(State.MovieList())
    }

    fun toMovieDetails(movie: Movie) {
        _activityState.setValue(State.MovieDetails(movie))
    }

    sealed class State {
        class MovieList : State()
        class MovieDetails(val selectedMovie: Movie) : State() {
        }
    }
}
