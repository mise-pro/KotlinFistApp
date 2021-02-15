package ru.promise.educationKtApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import ru.promise.educationKtApp.fragments.FragmentMovieDetails
import ru.promise.educationKtApp.fragments.FragmentMovieList
import ru.promise.educationKtApp.fragments.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private val mainActivityViewModel: MainActivityViewModel by viewModels { ViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainActivityViewModel.activityState.observe(this, { setupUIFragment() })
    }

    fun setupUIFragment() {
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
        fragment.initSubscription(mainActivityViewModel)
    }

    fun showMovieDetailsFragment() {
        //Log.w("showMovieDetailsFragment", selectedMovie.toString())
        val fragment = FragmentMovieDetails()
        supportFragmentManager.beginTransaction()
            .apply {
                add(R.id.mainFrame, fragment)
                commit()
            }
        fragment.initSubscription(mainActivityViewModel)
    }
}
