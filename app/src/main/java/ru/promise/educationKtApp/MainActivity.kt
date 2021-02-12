package ru.promise.educationKtApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import ru.promise.educationKtApp.fragments.FragmentMovieDetails
import ru.promise.educationKtApp.fragments.FragmentMovieList
import ru.promise.educationKtApp.fragments.ViewModelFactory
import ru.promise.educationKtApp.model.Movie

class MainActivity : AppCompatActivity() {

    private var selectedMovie: Movie? = null
    private val mainActivityViewModel: MainActivityViewModel by viewModels { ViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainActivityViewModel.activityState.observe(this, { setupFragment() })
        setupFragment()
    }

    fun setupFragment() {
        when (mainActivityViewModel.activityState.value) {
            is MainActivityViewModel.State.MovieList -> showMovieListFragment()
            is MainActivityViewModel.State.MovieDetails -> showMovieDetailsFragment()
        }
    }

    fun showMovieListFragment() {
        val fragment = FragmentMovieList()
        supportFragmentManager.beginTransaction()
            .apply {
                add(R.id.mainFrame, fragment)
                commit()
            }
        fragment.getFragmentEvents().selectedMovie.observe(this,
            { movie ->
                saveMovie(movie)
                //todo pretty strange implementation x2+1
                mainActivityViewModel.toMovieDetails(movie)
            })
    }

    fun saveMovie(movie: Movie) {
        //selectedMovie = movie
    }

    fun showMovieDetailsFragment() {
        Log.w("showMovieDetailsFragment", selectedMovie.toString())
        val fragment = FragmentMovieDetails()
        //fragment.setParam(mainActivityViewModel)
        supportFragmentManager.beginTransaction()
            .apply {
                add(R.id.mainFrame, fragment)
                commit()
            }
        fragment.initObserver(mainActivityViewModel)
    }

    companion object {
    }
}
