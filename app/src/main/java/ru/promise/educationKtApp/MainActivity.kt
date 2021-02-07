package ru.promise.educationKtApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.promise.educationKtApp.fragments.FragmentMoviesDetails
import ru.promise.educationKtApp.fragments.FragmentMoviesList
import ru.promise.educationKtApp.model.Movie

class MainActivity : AppCompatActivity(), IMovieSelectionListener, IBackToMovieListListener {

    private var moviesListFragment: FragmentMoviesList? = null
    private var movieDetailsFragment: FragmentMoviesDetails? = null
    private var selectedMovie: Movie? = null
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
        outState.putParcelable(SELECTED_MOVIE, selectedMovie)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        visibleFragment = savedInstanceState.getString(VISIBLE_FRAGMENT)
        selectedMovie = savedInstanceState.getParcelable(SELECTED_MOVIE)
        setupUi()
    }

    private fun setupUi() {
        if (visibleFragment == FragmentMoviesDetails.FRAGMENT_NAME) {
            movieDetailsFragmentCreate()
        }
    }

    private fun movieDetailsFragmentCreate() {
        movieDetailsFragment = FragmentMoviesDetails.newInstance(selectedMovie!!).apply { setBackToMovieListListener(this@MainActivity) }
        supportFragmentManager.beginTransaction()
                .apply {
                    add(R.id.mainFrame, movieDetailsFragment!!)
                    commit()
                }
    }

    override fun movieSelectionClick(movie: Movie) {
        visibleFragment = FragmentMoviesDetails.FRAGMENT_NAME
        selectedMovie = movie
        movieDetailsFragmentCreate()
    }

    override fun backToMovieListTransition() {
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
        private const val SELECTED_MOVIE = "SELECTED_MOVIE"
    }
}
interface IMovieSelectionListener {
    fun movieSelectionClick(movie: Movie)
}
interface IBackToMovieListListener{
    fun backToMovieListTransition()
}