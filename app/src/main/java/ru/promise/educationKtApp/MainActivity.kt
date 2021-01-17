package ru.promise.educationKtApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import ru.promise.educationKtApp.model.Movie

class MainActivity : AppCompatActivity(), IMovieSelectionListener, FragmentMoviesDetails.IFragmentMovieDetailsListener {

    private var moviesListFragment: FragmentMoviesList? = null
    private var movieDetailsFragment: FragmentMoviesDetails? = null
    private var selectedMovieId: Movie? = null
    private var visibleFragment: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        moviesListFragment = FragmentMoviesList().apply { sunscribeMovieSelection(this@MainActivity) }
        supportFragmentManager.beginTransaction()
                .apply {
                    add(R.id.mainFrame, moviesListFragment!!)
                    commit()
                }


        if (savedInstanceState == null) {
            visibleFragment = FragmentMoviesList.FRAGMENT_NAME
            setupUi()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(VISIBLE_FRAGMENT, visibleFragment)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        visibleFragment = savedInstanceState.getString(VISIBLE_FRAGMENT)
        setupUi()
    }

    private fun setupUi() {
        if (visibleFragment == FragmentMoviesDetails.FRAGMENT_NAME) {
            movieDetailsFragmentCreate()
        }
    }

    private fun movieDetailsFragmentCreate() {
        movieDetailsFragment = FragmentMoviesDetails().apply { setClickListener(this@MainActivity) }
        supportFragmentManager.beginTransaction()
                .apply {
                    add(R.id.mainFrame, movieDetailsFragment!!)
                    commit()
                }
    }

    override fun movieSelectionClick(movie: Movie) {
        visibleFragment = FragmentMoviesDetails.FRAGMENT_NAME
        selectedMovieId = movie
        Log.d("movieCardClick", movie.name)//todo refactor
        /*movieDetailsFragmentCreate()*/
    }

    override fun backButtonPressed() {
        visibleFragment = FragmentMoviesList.FRAGMENT_NAME
        supportFragmentManager.beginTransaction()
                .apply {
                    remove(movieDetailsFragment!!)
                    commit()
                }
        movieDetailsFragment = null
    }

    companion object {
        private const val VISIBLE_FRAGMENT = "VISIBLE_FRAGMENT"
    }
}
interface IMovieSelectionListener {
    fun movieSelectionClick(movie: Movie)
}