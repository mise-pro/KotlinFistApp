package ru.promise.educationKtApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(), FragmentMoviesList.IFragmentMoviesListListener, FragmentMoviesDetails.IFragmentMovieDetailsListener {

    private val moviesListFragment = FragmentMoviesList().apply { setClickListener(this@MainActivity) }
    private val movieDetailsFragment = FragmentMoviesDetails().apply { setClickListener(this@MainActivity) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .apply {
                add(R.id.mainFrame, moviesListFragment)
                commit()
            }
    }

    override fun movieCardClick(movieId: Int) {
        supportFragmentManager.beginTransaction()
            .apply {
                replace(R.id.mainFrame,movieDetailsFragment)
                commit()
                //TODO pass args to movieDetails
            }
    }

    override fun backButtonPressed() {
        supportFragmentManager.beginTransaction()
            .apply {
                replace(R.id.mainFrame,moviesListFragment)
                commit()
            }
    }
}